package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.model.responses.AnalysisGraphicResponse;
import com.codetech.focusstudentbackend.core.enums.FaceStatus;
import com.codetech.focusstudentbackend.core.repositories.AnalysisRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.IAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class AnalysisService implements IAnalysisService {

    private final AnalysisRepository analysisRepository;

    @Override
    public List<AnalysisGraphicResponse> getAnalysisGraphic(FaceStatus faceStatus) {
        return analysisRepository.findSectionTotals(faceStatus);
    }
}
