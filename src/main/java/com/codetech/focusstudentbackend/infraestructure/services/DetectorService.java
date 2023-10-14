package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.DetectorMapper;
import com.codetech.focusstudentbackend.api.model.requests.CreateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.responses.DetectorResponse;
import com.codetech.focusstudentbackend.core.entities.Analysis;
import com.codetech.focusstudentbackend.core.entities.Detector;
import com.codetech.focusstudentbackend.core.entities.Lesson;
import com.codetech.focusstudentbackend.core.entities.Student;
import com.codetech.focusstudentbackend.core.repositories.AnalysisRepository;
import com.codetech.focusstudentbackend.core.repositories.DetectorRepository;
import com.codetech.focusstudentbackend.core.repositories.LessonRepository;
import com.codetech.focusstudentbackend.core.repositories.StudentRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.IDetectorService;
import com.codetech.focusstudentbackend.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class DetectorService implements IDetectorService {

    private final Validator validator;
    private final DetectorRepository detectorRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final AnalysisRepository analysisRepository;
    private final DetectorMapper detectorMapper;

    @Override
    public List<DetectorResponse> getAll() {
        return detectorMapper.modelToList(detectorRepository.findAll());
    }

    @Override
    public DetectorResponse getById(Long detectorId) {

        Detector detector = detectorRepository.findById(detectorId).orElseThrow(() -> new NotFoundException("Detector no encontrado con el id: " + detectorId));

        return detectorMapper.toResponse(detector);
    }

    @Override
    public String create(CreateDetectorRequest request) {

        Set<ConstraintViolation<CreateDetectorRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new NotFoundException("Estudiante no encontrado con el id: " + request.getStudentId()));
        Lesson lesson = lessonRepository.findById(request.getLessonId()).orElseThrow(() -> new NotFoundException("Leccion no encontrado con el id: " + request.getLessonId()));

        Detector detector = Detector.builder()
                .start(request.getStart())
                .end(request.getEnd())
                .student(student)
                .lesson(lesson)
                .build();

        Detector newDetector = detectorRepository.save(detector);

        request.getAnalyses().forEach(analysis -> {
            Analysis newAnalysis = Analysis.builder()
                    .timeStatus(analysis.getTimeStatus())
                    .concentrateStatus(analysis.getConcentrateStatus())
                    .faceStatus(analysis.getFaceStatus())
                    .detector(newDetector)
                    .build();

            analysisRepository.save(newAnalysis);
        });


        return "Detector creado con exito!";

    }


    @Override
    public String delete(Long detectorId) {
        Detector detector = detectorRepository.findById(detectorId).orElseThrow(() -> new NotFoundException("Detector no encontrado"));

        detectorRepository.delete(detector);

        return "Detector eliminado con exito!";

    }
}
