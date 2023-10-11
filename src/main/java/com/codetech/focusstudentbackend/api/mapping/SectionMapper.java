package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.SectionResponse;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SectionMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public SectionResponse toResponse(Section model) {
        return mapper.map(model, SectionResponse.class);
    }

    public List<SectionResponse> modelToList(List<Section> modelList) {
        return mapper.mapList(modelList, SectionResponse.class);
    }
}
