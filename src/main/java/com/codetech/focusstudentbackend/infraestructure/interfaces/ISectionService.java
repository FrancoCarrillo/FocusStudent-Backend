package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.AddCourseToSection;
import com.codetech.focusstudentbackend.api.model.requests.CreateSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.SectionResponse;

import java.util.List;

public interface ISectionService {

    List<SectionResponse> getAll();

    SectionResponse getById(Long sectionId);

    String create(CreateSectionRequest request);

    SectionResponse update(Long courseId, UpdateSectionRequest request);

    String delete(Long sectionId);

}
