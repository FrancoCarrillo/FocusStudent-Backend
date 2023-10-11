package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.DetectorResponse;
import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.entities.Detector;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DetectorMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public DetectorResponse toResponse(Detector model) {
        return mapper.map(model, DetectorResponse.class);
    }

    public List<DetectorResponse> modelToList(List<Detector> modelList) {
        return mapper.mapList(modelList, DetectorResponse.class);
    }
}
