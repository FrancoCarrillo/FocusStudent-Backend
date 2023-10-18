package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LogInResponse {
    private Long id;
    private String name;
    private String token;
    private String role;
    private Long studentId;
    private Long teacherId;
    private Long sectionId;
}