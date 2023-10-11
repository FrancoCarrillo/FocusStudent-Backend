package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateDetectorRequest {
    @NotEmpty
    @NotNull
    private String initialState;

    @NotEmpty
    @NotNull
    private String middleState;

    @NotEmpty
    @NotNull
    private String finalState;

    @NotNull
    private Long studentId;

    @NotNull
    private Long lessonId;
}
