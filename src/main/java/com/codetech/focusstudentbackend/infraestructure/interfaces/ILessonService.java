package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.CreateLessonRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateLessonRequest;
import com.codetech.focusstudentbackend.api.model.responses.LessonResponse;

import java.util.List;

public interface ILessonService {
    List<LessonResponse> getAll();

    LessonResponse getById(Long lessonId);

    String create(CreateLessonRequest request);

    LessonResponse update(Long lessonId, UpdateLessonRequest request);

    String delete(Long lessonId);

    List<LessonResponse> getAllByCourseSectionId(Long courseSectionId);
}
