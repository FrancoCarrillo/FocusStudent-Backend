package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.core.entities.Student;
import com.codetech.focusstudentbackend.core.entities.Teacher;
import com.codetech.focusstudentbackend.core.entities.User;
import com.codetech.focusstudentbackend.core.repositories.*;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISecurityService;
import com.codetech.focusstudentbackend.utils.exceptions.NotFoundException;
import com.codetech.focusstudentbackend.utils.security.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class SecurityService implements ISecurityService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final Validator validator;

    @Override
    public LogInResponse login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        return new LogInResponse(user.getId(), user.getNames(), jwt, user.getRole().getName());
    }

    @Override
    public String register(CreateUserRequest createUserRequest) {

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(createUserRequest);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));


        if (Boolean.TRUE.equals(userRepository.existsByEmail(createUserRequest.getEmail()))) {
            throw new NotFoundException("El email ya esta en uso");
        }

        if (Boolean.TRUE.equals(userRepository.existsByDni(createUserRequest.getDni()))) {
            throw new NotFoundException("El DNI ya esta en uso");
        }

        if (Boolean.TRUE.equals(userRepository.existsByPhoneNumber(createUserRequest.getPhoneNumber()))) {
            throw new NotFoundException("El número de telefono ya esta en uso");
        }

        User user = User.builder()
                .names(createUserRequest.getNames())
                .lastNames(createUserRequest.getLastNames())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .dni(createUserRequest.getDni())
                .email(createUserRequest.getEmail())
                .address(createUserRequest.getAddress())
                .password(encoder.encode(createUserRequest.getPassword()))
                .build();


        Pattern pattern = Pattern.compile("_(.*?)@");
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.find(1))
            throw new NotFoundException("El email no es valido");


        String userRole = matcher.group(1);

        switch (userRole) {
            case "estudiante" -> {

                if (createUserRequest.getSectionId() == null) {
                    throw new NotFoundException("La seccion debe ser ingresada");
                }

                user.setRole(rolRepository.findByName("STUDENT"));

                User newUser = userRepository.save(user);
                Section studentSection = sectionRepository.findById(createUserRequest.getSectionId()).orElseThrow(() -> new NotFoundException("La seccion no existe"));

                Student student = Student.builder()
                        .section(studentSection)
                        .user(newUser).build();

                studentRepository.save(student);

            }
            case "profesor" -> {
                user.setRole(rolRepository.findByName("TEACHER"));

                User newUser = userRepository.save(user);

                Teacher teacher = Teacher.builder()
                        .user(newUser)
                        .build();


                teacherRepository.save(teacher);
            }
            default -> throw new NotFoundException("El email no es valido");

        }

        return "Registro de usuario exitoso!";
    }

}