package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.LessonResponse;
import com.codetech.focusstudentbackend.core.entities.Lesson;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LessonMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public LessonResponse toResponse(Lesson model) {
        return mapper.map(model, LessonResponse.class);
    }

    public List<LessonResponse> modelToList(List<Lesson> modelList) {
        return mapper.mapList(modelList, LessonResponse.class);
    }
}
