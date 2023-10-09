package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Integer year;
    private String description;

    @ManyToMany(mappedBy = "courses")
    public List<Teacher> teachers;

    @ManyToMany
    @JoinTable(
            name = "course_section",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
}
