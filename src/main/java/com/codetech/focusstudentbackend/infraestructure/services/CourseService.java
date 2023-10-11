package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.CourseMapper;
import com.codetech.focusstudentbackend.api.model.requests.CreateCourserRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateCourseRequest;
import com.codetech.focusstudentbackend.api.model.responses.CourseResponse;
import com.codetech.focusstudentbackend.core.entities.Course;
import com.codetech.focusstudentbackend.core.repositories.CourseRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ICourseService;
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
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;
    private final Validator validator;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseResponse> getAll() {
        return courseMapper.modelToList(courseRepository.findAll());
    }

    @Override
    public CourseResponse getById(Long courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Curso no encontrado con el id: " + courseId));

        return courseMapper.toResponse(course);
    }

    @Override
    public String create(CreateCourserRequest request) {
        Set<ConstraintViolation<CreateCourserRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        if (Boolean.TRUE.equals(courseRepository.existsByName(request.getName()))) {
            throw new NotFoundException("El nombre ya esta en uso");
        }

        Course course = Course.builder()
                .name(request.getName())
                .year(request.getYear())
                .description(request.getDescription())
                .build();

        courseRepository.save(course);

        return "Curso creado con exito!";

    }

    @Override
    public CourseResponse update(Long courseId, UpdateCourseRequest request) {
        Set<ConstraintViolation<UpdateCourseRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        course.setName(request.getName());
        course.setYear(request.getYear());
        course.setDescription(request.getDescription());


        courseRepository.save(course);

        return courseMapper.toResponse(course);

    }

    @Override
    public String delete(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        courseRepository.delete(course);

        return "Curso eliminado con exito!";
    }

    @Override
    public List<CourseResponse> getCoursesByTeacherId(Long teacherId) {

        return null;
    }
}
