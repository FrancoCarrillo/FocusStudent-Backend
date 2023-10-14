package com.codetech.focusstudentbackend.api.model.requests;

import com.codetech.focusstudentbackend.core.enums.ConcentrateStatus;
import com.codetech.focusstudentbackend.core.enums.FaceStatus;
import com.codetech.focusstudentbackend.core.enums.TimeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnalysisRequest {
        private TimeStatus timeStatus;
        private ConcentrateStatus concentrateStatus;
        private FaceStatus faceStatus;
}
