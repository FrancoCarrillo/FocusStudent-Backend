package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "teacher")
    private Set<CourseSection> courseSections = new HashSet<>();

    @ManyToOne
    private User user;
}
