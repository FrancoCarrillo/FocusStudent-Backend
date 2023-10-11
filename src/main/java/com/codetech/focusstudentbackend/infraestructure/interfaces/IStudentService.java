package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.responses.StudentResponse;

import java.util.List;

public interface IStudentService {

    List<StudentResponse> getAllBySectionId(Long sectionId);
}
