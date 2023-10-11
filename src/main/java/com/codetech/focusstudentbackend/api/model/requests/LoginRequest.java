package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;
}