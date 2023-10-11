package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {
    private Long id;
    private CourseSectionResponse courseSection;
    private String name;
    private Date initialTime;
    private Date finalTime;
}
