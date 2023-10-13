package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.CreateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.CourseResponse;
import com.codetech.focusstudentbackend.api.model.responses.CourseSectionResponse;

import java.util.List;

public interface ICourseSectionService {

        List<CourseSectionResponse> getAll();
        CourseSectionResponse getById(Long courseSectionId);

        String create(List<CreateCourseSectionRequest> request);

        String update(List<UpdateCourseSectionRequest> request);

        String delete(List<Long> courseSectionId);

        List<CourseSectionResponse> getAllByTeacherId(Long teacherId);
        List<CourseSectionResponse> getAllBySectionId(Long sectionId);
}
