package com.codetech.focusstudentbackend.api.model.requests;

import com.codetech.focusstudentbackend.core.entities.Analysis;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateDetectorRequest {

    @NotNull
    private Date start;

    @NotNull
    private Date end;

    @NotNull
    private List<CreateAnalysisRequest> analyses;

    @NotNull
    private Long studentId;

    @NotNull
    private Long lessonId;
}
