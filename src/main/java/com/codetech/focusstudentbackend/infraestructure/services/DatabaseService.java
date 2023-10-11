package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.core.entities.Role;
import com.codetech.focusstudentbackend.core.repositories.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class DatabaseService {
    private RolRepository rolRepository;

    private static final String[] DEFAULT_ROLES = {"STUDENT", "TEACHER", "ADMIN"};

    public void seedRol() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            if (!rolRepository.existsByName(name)) {
                rolRepository.save(new Role().withName(name));
            }
        });
    }

}
