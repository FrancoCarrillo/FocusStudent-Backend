package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.CourseSectionMapper;
import com.codetech.focusstudentbackend.api.model.requests.CreateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.CourseSectionResponse;
import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.entities.CourseSection;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.core.entities.Teacher;
import com.codetech.focusstudentbackend.core.repositories.CourseRepository;
import com.codetech.focusstudentbackend.core.repositories.CourseSectionRepository;
import com.codetech.focusstudentbackend.core.repositories.SectionRepository;
import com.codetech.focusstudentbackend.core.repositories.TeacherRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ICourseSectionService;
import com.codetech.focusstudentbackend.utils.exceptions.NotFoundException;
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
public class CourseSectionService implements ICourseSectionService {

    private final Validator validator;
    private final CourseSectionRepository courseSectionRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final CourseSectionMapper courseSectionMapper;

    @Override
    public List<CourseSectionResponse> getAll() {
        return courseSectionMapper.modelToList(courseSectionRepository.findAll());
    }

    @Override
    public CourseSectionResponse getById(Long courseSectionId) {
        return courseSectionMapper.toResponse(courseSectionRepository.findById(courseSectionId)
                .orElseThrow(() -> new NotFoundException("Sección de curso no encontrado con el id: " + courseSectionId)));
    }

    @Override
    public String create(List<CreateCourseSectionRequest> request) {
        Set<ConstraintViolation<List<CreateCourseSectionRequest>>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        request.forEach(courseSectionRequest -> {
            Teacher teacher = teacherRepository.findById(courseSectionRequest.getTeacherId()).orElseThrow(() -> new NotFoundException("Profesor no encontrado con el id: " + courseSectionRequest.getTeacherId()));
            Course course = courseRepository.findById(courseSectionRequest.getCourseId()).orElseThrow(() -> new NotFoundException("Curso no encontrado con el id: " + courseSectionRequest.getCourseId()));
            Section section = sectionRepository.findById(courseSectionRequest.getSectionId()).orElseThrow(() -> new NotFoundException("Sección no encontrada con el id: " + courseSectionRequest.getSectionId()));

            CourseSection courseSection = CourseSection.builder()
                    .teacher(teacher)
                    .course(course)
                    .section(section)
                    .build();

            courseSectionRepository.save(courseSection);

        });

        return "Secciónes creadas con exito!";

    }

    @Override
    public CourseSectionResponse update(Long courseSectionId, UpdateCourseSectionRequest request) {
        Set<ConstraintViolation<UpdateCourseSectionRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        Teacher teacher = teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new NotFoundException("Profesor no encontrado con el id: " + request.getTeacherId()));
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new NotFoundException("Curso no encontrado con el id: " + request.getCourseId()));
        Section section = sectionRepository.findById(request.getSectionId()).orElseThrow(() -> new NotFoundException("Sección no encontrada con el id: " + request.getSectionId()));
        CourseSection courseSection = courseSectionRepository.findById(courseSectionId).orElseThrow(() -> new NotFoundException("Sección de curso no encontrado con el id: " + courseSectionId));

        courseSection.setTeacher(teacher);
        courseSection.setCourse(course);
        courseSection.setSection(section);

        courseSectionRepository.save(courseSection);

        return courseSectionMapper.toResponse(courseSection);
    }

    @Override
    public String delete(Long courseSectionId) {
        CourseSection courseSection = courseSectionRepository.findById(courseSectionId).orElseThrow(() -> new NotFoundException("Sección de curso no encontrado con el id: " + courseSectionId));
        courseSectionRepository.delete(courseSection);
        return "Sección de curso eliminado con exito!";
    }

    @Override
    public List<CourseSectionResponse> getAllByTeacherId(Long teacherId) {
        return courseSectionMapper.modelToList(courseSectionRepository.findAllByTeacherId(teacherId));
    }

    @Override
    public List<CourseSectionResponse> getAllBySectionId(Long sectionId) {
        return courseSectionMapper.modelToList(courseSectionRepository.findAllBySectionId(sectionId));
    }
}
