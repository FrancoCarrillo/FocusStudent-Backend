package com.codetech.focusstudentbackend.core.repositories;

import com.codetech.focusstudentbackend.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    boolean existsByName(String name);
}
