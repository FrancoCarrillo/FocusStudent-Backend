package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.model.requests.AddCourseToSection;
import com.codetech.focusstudentbackend.api.model.requests.CreateSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.AddCourseToSectionResponse;
import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.core.repositories.CourseRepository;
import com.codetech.focusstudentbackend.core.repositories.SectionRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISectionService;
import com.codetech.focusstudentbackend.utils.exceptions.NotFoundException;
import com.sun.jdi.InternalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class SectionService implements ISectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final Validator validator;

    @Override
    public String create(CreateSectionRequest request) {
        Set<ConstraintViolation<CreateSectionRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        if (Boolean.TRUE.equals(sectionRepository.existsByName(request.getName()))) {
            throw new NotFoundException("El nombre ya esta en uso");
        }

        Section section = Section.builder()
                .name(request.getName())
                .build();

        sectionRepository.save(section);

        return "Seccion creada con exito!";
    }

    @Transactional
    @Override
    public String addCourseToSection(Long sectionId, AddCourseToSection request) {

        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new NotFoundException("Seccion no encontrada"));

        Course courses = courseRepository.findById(request.getCourseIds()).orElseThrow(() -> new NotFoundException("Curso no encontrado"));

//        section.getCourses().forEach(course -> {
//            if (courses.contains(course)) {
//                throw new NotFoundException("El curso ya esta en la seccion");
//            }
//        });


        try {
            section.getCourses().add(courses);
            sectionRepository.save(section);

        } catch (Exception e) {
            throw new InternalException("Problem adding course to section");
        }

        return "Cursos agregados con exito!";
    }

}
