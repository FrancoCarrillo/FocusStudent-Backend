package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastNames;
    private String phoneNumber;
    private String dni;
    private String email;
    private String address;
    private String password;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Student> students = new HashSet<>();

}
