package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.RegisterUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;
import com.codetech.focusstudentbackend.core.entities.Role;
import com.codetech.focusstudentbackend.core.entities.User;
import com.codetech.focusstudentbackend.core.repositories.RolRepository;
import com.codetech.focusstudentbackend.core.repositories.UserRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISecurityService;
import com.codetech.focusstudentbackend.utils.exceptions.NotFoundException;
import com.codetech.focusstudentbackend.utils.security.jwt.JwtTokenUtil;
import com.sun.jdi.InternalException;
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
import java.util.HashSet;
import java.util.Objects;
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

        return new LogInResponse(user.getId(), jwt, user.getRole().getName());
    }

    @Override
    public String register(RegisterUserRequest registerUserRequest) {

        Set<ConstraintViolation<RegisterUserRequest>> violations = validator.validate(registerUserRequest);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));


        if (Boolean.TRUE.equals(userRepository.existsByEmail(registerUserRequest.getEmail()))) {
            throw new NotFoundException("El email ya esta en uso");
        }

        if (Boolean.TRUE.equals(userRepository.existsByDni(registerUserRequest.getDni()))) {
            throw new NotFoundException("El DNI ya esta en uso");
        }


        User user = User.builder()
                .names(registerUserRequest.getNames())
                .lastNames(registerUserRequest.getLastNames())
                .phoneNumber(registerUserRequest.getPhoneNumber())
                .dni(registerUserRequest.getDni())
                .email(registerUserRequest.getEmail())
                .address(registerUserRequest.getAddress())
                .password(encoder.encode(registerUserRequest.getPassword()))
                .build();


        Pattern pattern = Pattern.compile("_(.*?)@");
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.find(1))
            throw new NotFoundException("El email no es valido");

        String userRole = matcher.group(1);
        
        switch (userRole) {
            case "estudiante" -> user.setRole(rolRepository.findByName("STUDENT"));
            case "profesor" -> user.setRole(rolRepository.findByName("TEACHER"));
            case "admin" -> user.setRole(rolRepository.findByName("ADMIN"));
            default -> {
                throw new NotFoundException("El email no es valido");
            }
        }

        userRepository.save(user);

        return "Registro de usuario exitoso!";
    }

}