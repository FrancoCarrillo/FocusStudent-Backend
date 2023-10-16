package com.codetech.focusstudentbackend.core.repositories;

import com.codetech.focusstudentbackend.core.entities.Role;
import com.codetech.focusstudentbackend.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Boolean existsByEmail(String email);

    Boolean existsByDni(String dni);

    Boolean existsByPhoneNumber(String phoneNumber);

    List<User> findAllByRoleAndLastNamesStartsWith(Role rol, String lastNames);

}