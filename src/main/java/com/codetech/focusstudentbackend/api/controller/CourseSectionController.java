package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.CreateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.CourseSectionResponse;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ICourseSectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course_section")
@AllArgsConstructor
@Tag(name = "Course Section")
public class CourseSectionController {

    private final ICourseSectionService courseSectionService;

    @Operation(summary = "Get all the course-sections")
    @GetMapping()
    public ResponseEntity<List<CourseSectionResponse>> getAll() {
        return ResponseEntity.ok(courseSectionService.getAll());
    }

    @Operation(summary = "Get course-sections by ID")
    @GetMapping("/{courseSectionId}")
    public ResponseEntity<CourseSectionResponse> getById(@PathVariable Long courseSectionId) {
        return ResponseEntity.ok(courseSectionService.getById(courseSectionId));
    }

    @Operation(summary = "Update a course-sections")
    @PutMapping("{courseSectionId}")
    public ResponseEntity<CourseSectionResponse> update(@PathVariable Long courseSectionId, @RequestBody UpdateCourseSectionRequest request) {
        return ResponseEntity.ok(courseSectionService.update(courseSectionId, request));
    }

    @Operation(summary = "Delete a course-sections")
    @DeleteMapping("{courseSectionId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long courseSectionId) {
        return ResponseEntity.ok(new MessageResponse(courseSectionService.delete(courseSectionId)));
    }

    @Operation(summary = "Create a course-section")
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody List<CreateCourseSectionRequest> request) {
        return ResponseEntity.ok(new MessageResponse(courseSectionService.create(request)));
    }

    @Operation(summary = "Get all the course-sections by teacher")
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseSectionResponse>> getAllByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseSectionService.getAllByTeacherId(teacherId));
    }

    @Operation(summary = "Get all the course-sections by section")
    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<CourseSectionResponse>> getAllBySectionId(@PathVariable Long sectionId) {
        return ResponseEntity.ok(courseSectionService.getAllBySectionId(sectionId));
    }

}
