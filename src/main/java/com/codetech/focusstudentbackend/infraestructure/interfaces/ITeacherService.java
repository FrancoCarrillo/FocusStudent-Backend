package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.responses.TeacherResponse;

import java.util.List;

public interface ITeacherService {
    List<TeacherResponse> getAll();
}
