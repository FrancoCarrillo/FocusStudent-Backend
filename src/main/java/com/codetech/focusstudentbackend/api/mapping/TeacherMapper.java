package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.TeacherResponse;
import com.codetech.focusstudentbackend.core.entities.Teacher;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeacherMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public TeacherResponse toResponse(Teacher model) {
        return mapper.map(model, TeacherResponse.class);
    }

    public List<TeacherResponse> modelToList(List<Teacher> modelList) {
        return mapper.mapList(modelList, TeacherResponse.class);
    }
}
