package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.StudentResponse;
import com.codetech.focusstudentbackend.core.entities.Student;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public StudentResponse toResponse(Student model) {
        return mapper.map(model, StudentResponse.class);
    }

    public List<StudentResponse> modelToList(List<Student> modelList) {
        return mapper.mapList(modelList, StudentResponse.class);
    }
}
