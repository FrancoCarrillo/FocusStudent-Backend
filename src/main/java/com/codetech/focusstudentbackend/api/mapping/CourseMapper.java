package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.CourseResponse;
import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public CourseResponse toResponse(Course model) {
        return mapper.map(model, CourseResponse.class);
    }

    public List<CourseResponse> modelToList(List<Course> modelList) {
        return mapper.mapList(modelList, CourseResponse.class);
    }

}
