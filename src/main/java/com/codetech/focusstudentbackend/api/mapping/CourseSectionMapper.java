package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.CourseResponse;
import com.codetech.focusstudentbackend.api.model.responses.CourseSectionResponse;
import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.entities.CourseSection;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseSectionMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public CourseSectionResponse toResponse(CourseSection model) {
        return mapper.map(model, CourseSectionResponse.class);
    }

    public List<CourseSectionResponse> modelToList(List<CourseSection> modelList) {
        return mapper.mapList(modelList, CourseSectionResponse.class);
    }
}
