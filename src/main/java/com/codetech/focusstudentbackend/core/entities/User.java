package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
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


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
