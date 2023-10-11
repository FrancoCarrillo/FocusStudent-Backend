package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {
    private Long id;
    private CourseResponse course;
}
