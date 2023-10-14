package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.responses.AnalysisGraphicResponse;
import com.codetech.focusstudentbackend.core.enums.FaceStatus;
import com.codetech.focusstudentbackend.infraestructure.interfaces.IAnalysisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/analysis")
@AllArgsConstructor
@Tag(name = "Analysis")
public class AnalysisController {

    private final IAnalysisService analysisService;

    @GetMapping("/{faceStatus}")
    public ResponseEntity<List<AnalysisGraphicResponse>> getAnalysisGraphic(@PathVariable FaceStatus faceStatus) {
        return ResponseEntity.ok(analysisService.getAnalysisGraphic(faceStatus));
    }

}
