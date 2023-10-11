package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DetectorResponse {
    private Long id;
    private String initialState;
    private String middleState;
    private String finalState;
    private Date detectionDate;
    private StudentResponse student;
    private LessonResponse lesson;
}
