package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Long id;
    private String name;
    private Integer year;
    private String description;


}
