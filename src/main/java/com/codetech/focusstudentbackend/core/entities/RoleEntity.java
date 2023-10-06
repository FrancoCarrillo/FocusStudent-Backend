package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    public Set<UserEntity> users = new HashSet<>();
}
