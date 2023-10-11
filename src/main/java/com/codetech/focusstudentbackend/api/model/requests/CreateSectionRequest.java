package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CreateSectionRequest {

    @NotEmpty
    @NotNull
    private String name;

}
