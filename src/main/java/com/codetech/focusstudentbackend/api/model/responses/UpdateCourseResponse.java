package com.codetech.focusstudentbackend.api.model.responses;

import com.codetech.focusstudentbackend.core.entities.Lesson;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.core.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCourseResponse {
    private Long id;
    private Integer year;
    private String name;
    private String description;
    public List<Teacher> teachers;
    private Set<Section> sections = new HashSet<>();
    private List<Lesson> lessons;
}
