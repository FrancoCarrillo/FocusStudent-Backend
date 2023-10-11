package com.codetech.focusstudentbackend.api.model.responses;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String names;
    private String lastNames;
    private String phoneNumber;
    private String dni;
    private String email;
    private String address;

}
