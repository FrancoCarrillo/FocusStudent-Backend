package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterUserRequest {

    @NotEmpty
    @NotNull
    private String names;

    @NotEmpty
    @NotNull
    private String lastNames;

    @NotEmpty
    @NotNull
    private String phoneNumber;

    @Length(min = 8, max = 8, message = "DNI should be 8 characters")
    private String dni;


    @NotEmpty
    @NotNull
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty
    @NotNull
    private String address;

    @NotEmpty
    @NotNull
    @Length(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
