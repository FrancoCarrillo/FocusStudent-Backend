package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.CreateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.responses.DetectorResponse;

import java.util.List;

public interface IDetectorService {
    List<DetectorResponse> getAll();
    DetectorResponse getById(Long detectorId);

    String create(CreateDetectorRequest request);

    DetectorResponse update(Long detectorId, UpdateDetectorRequest request);

    String delete(Long detectorId);
}
