package com.codetech.focusstudentbackend.api.model.responses;

import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.core.entities.Teacher;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionResponse {
    private Long id;
    private CourseResponse course;
    private SectionResponse section;
    private TeacherResponse teacher;
}
