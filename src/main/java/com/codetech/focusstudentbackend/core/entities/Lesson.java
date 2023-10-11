package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "lesson")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date initialTime;
    private Date finalTime;

    @ManyToOne
    private CourseSection courseSection;

    @OneToMany(mappedBy = "lesson")
    private Set<Detector> detectors;
}
