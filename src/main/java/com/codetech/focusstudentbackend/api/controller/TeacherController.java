package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.responses.TeacherResponse;
import com.codetech.focusstudentbackend.infraestructure.services.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
@AllArgsConstructor
@Tag(name = "Teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "Get all the teachers")
    @GetMapping()
    public ResponseEntity<List<TeacherResponse>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

}
