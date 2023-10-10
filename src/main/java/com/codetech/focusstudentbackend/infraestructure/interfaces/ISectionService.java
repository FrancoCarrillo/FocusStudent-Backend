package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.AddCourseToSection;
import com.codetech.focusstudentbackend.api.model.requests.CreateSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.AddCourseToSectionResponse;
import com.codetech.focusstudentbackend.core.entities.Section;

public interface ISectionService {

    String create(CreateSectionRequest request);
    String addCourseToSection(Long sectionId, AddCourseToSection request);
}
