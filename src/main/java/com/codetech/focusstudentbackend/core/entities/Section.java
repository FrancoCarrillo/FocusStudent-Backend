package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "section")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "section")
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "section")
    private Set<CourseSection> courses = new HashSet<>();
}
