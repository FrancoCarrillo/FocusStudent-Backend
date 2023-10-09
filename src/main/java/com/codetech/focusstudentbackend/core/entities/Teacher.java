package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class Teacher extends User {

    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private List<Course> courses;
}
