package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
