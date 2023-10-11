package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UpdateLessonRequest {
    @NotNull
    private Long courseSectionId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Date initialTime;

    @NotNull
    private Date finalTime;
}
