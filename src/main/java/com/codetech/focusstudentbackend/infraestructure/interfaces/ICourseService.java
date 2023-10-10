package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.CreateCourserRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateCourseRequest;
import com.codetech.focusstudentbackend.api.model.responses.UpdateCourseResponse;

public interface ICourseService {

    String create(CreateCourserRequest request);

    UpdateCourseResponse update(Long courseId, UpdateCourseRequest request);

    String delete(Long courseId);
}
