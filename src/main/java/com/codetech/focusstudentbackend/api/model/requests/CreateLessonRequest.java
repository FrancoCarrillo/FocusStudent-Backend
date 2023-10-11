package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateLessonRequest {

    @NotNull
    private Long courseId;
}
