package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.SectionMapper;
import com.codetech.focusstudentbackend.api.model.requests.CreateSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.SectionResponse;
import com.codetech.focusstudentbackend.core.entities.Section;
import com.codetech.focusstudentbackend.core.repositories.CourseRepository;
import com.codetech.focusstudentbackend.core.repositories.SectionRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISectionService;
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
public class SectionService implements ISectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final Validator validator;
    private final SectionMapper sectionMapper;

    @Override
    public List<SectionResponse> getAll() {
        return sectionMapper.modelToList(sectionRepository.findAll());
    }

    @Override
    public SectionResponse getById(Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new NotFoundException("Seccion no encontrada"));
        return sectionMapper.toResponse(section);
    }

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

    @Override
    public SectionResponse update(Long sectionId, UpdateSectionRequest request) {
        Set<ConstraintViolation<UpdateSectionRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new NotFoundException("Seccion no encontrada"));

        if (Boolean.TRUE.equals(sectionRepository.existsByName(request.getName())) && !section.getName().equals(request.getName())) {
            throw new NotFoundException("El nombre ya esta en uso");
        }

        section.setName(request.getName());

        return sectionMapper.toResponse(section);
    }

    @Override
    public String delete(Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new NotFoundException("Seccion no encontrada"));

        sectionRepository.delete(section);

        return "Seccion eliminada con exito!";

    }

}
