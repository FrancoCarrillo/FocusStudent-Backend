package com.codetech.focusstudentbackend.api.model.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCourseSectionRequest {

    @NotNull
    private Long courseId;
    @NotNull
    private Long sectionId;
    @NotNull
    private Long teacherId;
}
