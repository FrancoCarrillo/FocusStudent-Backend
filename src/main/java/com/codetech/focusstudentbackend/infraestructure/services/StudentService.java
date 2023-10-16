package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.StudentMapper;
import com.codetech.focusstudentbackend.api.model.responses.StudentResponse;
import com.codetech.focusstudentbackend.core.repositories.SectionRepository;
import com.codetech.focusstudentbackend.core.repositories.StudentRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.IStudentService;
import com.codetech.focusstudentbackend.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final SectionRepository sectionRepository;

    @Override
    public List<StudentResponse> getAllBySectionId(Long sectionId) {

        if (!sectionRepository.existsById(sectionId))
            throw new NotFoundException("Seccion no encontrada con el id: " + sectionId);

        return studentMapper.modelToList(studentRepository.findAllBySectionId(sectionId));
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentMapper.modelToList(studentRepository.findAll());
    }
}
