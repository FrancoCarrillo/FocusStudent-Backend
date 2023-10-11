package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private SectionResponse section;
    private UserResponse user;
}
