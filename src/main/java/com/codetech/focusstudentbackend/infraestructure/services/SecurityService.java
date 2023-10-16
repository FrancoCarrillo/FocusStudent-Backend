package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.UserMapper;
import com.codetech.focusstudentbackend.api.model.requests.CreateUserRequest;
import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;
import com.codetech.focusstudentbackend.api.model.responses.UserResponse;
import com.codetech.focusstudentbackend.core.entities.*;
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
import java.util.List;
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
    private final UserMapper userMapper;

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
            case "admin" -> {
                user.setRole(rolRepository.findByName("ADMIN"));

                userRepository.save(user);

            }
            default -> throw new NotFoundException("El email no es valido");

        }

        return "Registro de usuario exitoso!";
    }

    @Override
    public UserResponse update(Long userId, UpdateUserRequest request) {
        Set<ConstraintViolation<UpdateUserRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (Boolean.TRUE.equals(userRepository.existsByEmail(request.getEmail())) && !user.getEmail().equals(request.getEmail())) {
            throw new NotFoundException("El email ya esta en uso");
        }

        if (Boolean.TRUE.equals(userRepository.existsByDni(request.getDni())) && !user.getDni().equals(request.getDni())) {
            throw new NotFoundException("El DNI ya esta en uso");
        }

        if (Boolean.TRUE.equals(userRepository.existsByPhoneNumber(request.getPhoneNumber())) && !user.getPhoneNumber().equals(request.getPhoneNumber())) {
            throw new NotFoundException("El número de telefono ya esta en uso");
        }

        user.setNames(request.getNames());
        user.setLastNames(request.getLastNames());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());

        Pattern pattern = Pattern.compile("_(.*?)@");
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.find(1))
            throw new NotFoundException("El email no es valido");


        String userRole = matcher.group(1);

        switch (userRole) {
            case "estudiante" -> {

                if (request.getSectionId() == null) {
                    throw new NotFoundException("La seccion debe ser ingresada");
                }

                user.setRole(rolRepository.findByName("STUDENT"));

                Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

                Section studentSection = sectionRepository.findById(request.getSectionId()).orElseThrow(() -> new NotFoundException("La seccion no existe"));

                student.setSection(studentSection);

                studentRepository.save(student);

            }
            case "profesor" -> user.setRole(rolRepository.findByName("TEACHER"));
            case "admin" -> user.setRole(rolRepository.findByName("ADMIN"));
            default -> throw new NotFoundException("El email no es valido");

        }

        userRepository.save(user);

        return userMapper.toResponse(user);

    }

    @Override
    public List<UserResponse> getAllUsersByLastNameAndRol(String lastName, String rol) {

        Role role = rolRepository.findByName(rol);

        return userMapper.modelToList(userRepository.findAllByRoleAndLastNamesStartsWith(role, lastName));
    }

    @Override
    public String changePassword(Long userId, String password) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        user.setPassword(encoder.encode(password));

        userRepository.save(user);

        return "Contraseña cambiada con exito";

    }

}