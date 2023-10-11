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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @ManyToOne
    public Course course;

    @ManyToOne
    public Section section;

    @ManyToOne
    public Teacher teacher;
}
