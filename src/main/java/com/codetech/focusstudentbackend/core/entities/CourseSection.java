package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "course_section")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class CourseSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Section section;

    @ManyToOne
    private Teacher teacher;
}
