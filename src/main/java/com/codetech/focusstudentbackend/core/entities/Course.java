package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String grade;
    private String section;
    private Integer year;

    @ManyToMany(mappedBy = "courses")
    public Set<User> users = new HashSet<>();
}
