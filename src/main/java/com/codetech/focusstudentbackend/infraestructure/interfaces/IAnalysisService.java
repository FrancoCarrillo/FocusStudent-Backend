package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.responses.AnalysisGraphicResponse;
import com.codetech.focusstudentbackend.core.enums.FaceStatus;

import java.util.List;

public interface IAnalysisService {
    List<AnalysisGraphicResponse> getAnalysisGraphic(FaceStatus faceStatus);
}
