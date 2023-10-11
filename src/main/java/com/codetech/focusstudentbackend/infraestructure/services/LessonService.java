package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.LessonMapper;
import com.codetech.focusstudentbackend.api.model.requests.CreateLessonRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateLessonRequest;
import com.codetech.focusstudentbackend.api.model.responses.LessonResponse;
import com.codetech.focusstudentbackend.core.entities.CourseSection;
import com.codetech.focusstudentbackend.core.entities.Lesson;
import com.codetech.focusstudentbackend.core.repositories.CourseSectionRepository;
import com.codetech.focusstudentbackend.core.repositories.LessonRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ILessonService;
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
public class LessonService implements ILessonService {

    private final Validator validator;
    private final LessonRepository lessonRepository;
    private final CourseSectionRepository courseSectionRepository;
    private final LessonMapper lessonMapper;

    @Override
    public List<LessonResponse> getAll() {
        return lessonMapper.modelToList(lessonRepository.findAll());
    }

    @Override
    public LessonResponse getById(Long lessonId) {

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException("Leccion no encontrada con el id: " + lessonId));

        return lessonMapper.toResponse(lesson);
    }

    @Override
    public String create(CreateLessonRequest request) {

        Set<ConstraintViolation<CreateLessonRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        CourseSection courseSection = courseSectionRepository.findById(request.getCourseSectionId()).orElseThrow(() -> new NotFoundException("Seccion de curso no encontrado con el id: " + request.getCourseSectionId()));

        Lesson lesson = Lesson.builder()
                .courseSection(courseSection)
                .finalTime(request.getFinalTime())
                .name(request.getName())
                .initialTime(request.getInitialTime())
                .build();

        lessonRepository.save(lesson);

        return "Leccion creada exitosamente";
    }

    @Override
    public LessonResponse update(Long lessonId, UpdateLessonRequest request) {
        Set<ConstraintViolation<UpdateLessonRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        CourseSection courseSection = courseSectionRepository.findById(request.getCourseSectionId()).orElseThrow(() -> new NotFoundException("Seccion de curso no encontrado con el id: " + request.getCourseSectionId()));

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException("Leccion no encontrada con el id: " + lessonId));

        lesson.setCourseSection(courseSection);
        lesson.setFinalTime(request.getFinalTime());
        lesson.setName(request.getName());
        lesson.setInitialTime(request.getInitialTime());

        lessonRepository.save(lesson);

        return lessonMapper.toResponse(lesson);
    }

    @Override
    public String delete(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException("Leccion no encontrado"));

        lessonRepository.delete(lesson);

        return "Leccion eliminado con exito!";
    }

    @Override
    public List<LessonResponse> getAllByCourseSectionId(Long courseSectionId) {
        return lessonMapper.modelToList(lessonRepository.findAllByCourseSectionId(courseSectionId));
    }
}
