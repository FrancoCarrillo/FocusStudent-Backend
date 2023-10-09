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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String names;
    private String lastNames;
    private String phoneNumber;
    private String dni;
    private String email;
    private String address;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_course",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
