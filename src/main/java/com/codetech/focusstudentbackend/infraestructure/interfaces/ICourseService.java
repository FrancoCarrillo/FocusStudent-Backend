package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.CreateCourserRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateCourseRequest;
import com.codetech.focusstudentbackend.api.model.responses.CourseResponse;
import com.codetech.focusstudentbackend.api.model.responses.UpdateCourseResponse;

import java.util.List;

public interface ICourseService {

    List<CourseResponse> getAll();
    CourseResponse getById(Long courseId);

    String create(CreateCourserRequest request);

    UpdateCourseResponse update(Long courseId, UpdateCourseRequest request);

    String delete(Long courseId);
}
