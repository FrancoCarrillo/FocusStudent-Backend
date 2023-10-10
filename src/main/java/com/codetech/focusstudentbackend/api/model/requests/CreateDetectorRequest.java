package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateDetectorRequest {

    @NotEmpty
    @NotNull
    private String initialState;

    @NotEmpty
    @NotNull
    private String middleState;

    @NotEmpty
    @NotNull
    private String finalState;

    @NotEmpty
    @NotNull
    private Date detectionDate;

    @NotEmpty
    @NotNull
    private Long studentOd;

    @NotEmpty
    @NotNull
    private Long lessonId;
}
