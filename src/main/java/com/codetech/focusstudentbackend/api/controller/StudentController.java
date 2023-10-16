package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.responses.StudentResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/student")
@AllArgsConstructor
@Tag(name = "Student")
public class StudentController {

    private final IStudentService studentService;

    @Operation(summary = "Get all students")
    @GetMapping()
    public ResponseEntity<List<StudentResponse>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @Operation(summary = "Get all the students by section")
    @GetMapping("/{sectionId}")
    public ResponseEntity<List<StudentResponse>> getAllBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(studentService.getAllBySectionId(sectionId));
    }
}
