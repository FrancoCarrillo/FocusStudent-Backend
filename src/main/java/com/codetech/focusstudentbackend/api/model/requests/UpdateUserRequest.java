package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class UpdateUserRequest {
    @NotEmpty
    @NotNull
    private String names;

    @NotEmpty
    @NotNull
    private String lastNames;

    @NotEmpty
    @NotNull
    @Length(min = 9, max = 9, message = "El numero de telefono debe tener 9 dígitos")
    private String phoneNumber;

    @Length(min = 8, max = 8, message = "El número de DNI debe tener 8 dígitos")
    private String dni;


    @NotEmpty
    @NotNull
    @Email(message = "Formato de correo electrónico incorrecto")
    private String email;

    @NotEmpty
    @NotNull
    private String address;

    private Long sectionId;

    private Long studentId;
}
