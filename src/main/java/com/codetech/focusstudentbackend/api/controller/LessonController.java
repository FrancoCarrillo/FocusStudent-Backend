package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.CreateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateLessonRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateLessonRequest;
import com.codetech.focusstudentbackend.api.model.responses.DetectorResponse;
import com.codetech.focusstudentbackend.api.model.responses.LessonResponse;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ILessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lesson")
@AllArgsConstructor
@Tag(name = "Lesson")
public class LessonController {

    private final ILessonService lessonService;

    @Operation(summary = "Get all the lessons")
    @GetMapping()
    public ResponseEntity<List<LessonResponse>> getAll() {
        return ResponseEntity.ok(lessonService.getAll());
    }

    @Operation(summary = "Get lesson by ID")
    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> getById(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.getById(lessonId));
    }

    @Operation(summary = "Create a lesson")
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody CreateLessonRequest request) {
        return ResponseEntity.ok(new MessageResponse(lessonService.create(request)));
    }

    @Operation(summary = "Update a lesson")
    @PutMapping("{lessonId}")
    public ResponseEntity<LessonResponse> update(@PathVariable Long lessonId, @RequestBody UpdateLessonRequest request) {
        return ResponseEntity.ok(lessonService.update(lessonId, request));
    }

    @Operation(summary = "Lesson a detector")
    @DeleteMapping("{lessonId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long lessonId) {
        return ResponseEntity.ok(new MessageResponse(lessonService.delete(lessonId)));
    }

    @Operation(summary = "Get lesson by couse-section")
    @GetMapping("/course-section/{courseSectionId}")
    public ResponseEntity<List<LessonResponse>> getAllByCourseSection(@PathVariable Long courseSectionId) {
        return ResponseEntity.ok(lessonService.getAllByCourseSectionId(courseSectionId));
    }

}
