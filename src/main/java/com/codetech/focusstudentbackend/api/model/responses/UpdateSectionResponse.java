package com.codetech.focusstudentbackend.api.model.responses;

import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateSectionResponse {

    private Long id;
    private String name;
    private List<Student> students;
    private List<Course> courses;
}
