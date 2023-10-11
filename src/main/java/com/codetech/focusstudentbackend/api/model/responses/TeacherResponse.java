package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {
    private Long id;
    private UserResponse user;
}
