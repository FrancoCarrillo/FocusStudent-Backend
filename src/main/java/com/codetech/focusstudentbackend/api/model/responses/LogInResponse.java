package com.codetech.focusstudentbackend.api.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogInResponse {
    private Long id;
    private String token;
    private String role;
}