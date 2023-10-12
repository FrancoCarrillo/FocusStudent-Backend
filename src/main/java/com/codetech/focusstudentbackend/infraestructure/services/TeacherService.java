package com.codetech.focusstudentbackend.infraestructure.services;

import com.codetech.focusstudentbackend.api.mapping.TeacherMapper;
import com.codetech.focusstudentbackend.api.model.responses.TeacherResponse;
import com.codetech.focusstudentbackend.core.repositories.TeacherRepository;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ITeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherResponse> getAll() {
        return teacherMapper.modelToList(teacherRepository.findAll());
    }
}
