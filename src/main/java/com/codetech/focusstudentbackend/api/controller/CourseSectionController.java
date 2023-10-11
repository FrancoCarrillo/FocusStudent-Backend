package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.CreateCourseSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateCourserRequest;
import com.codetech.focusstudentbackend.api.model.responses.CourseResponse;
import com.codetech.focusstudentbackend.api.model.responses.CourseSectionResponse;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ICourseSectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course_section")
@AllArgsConstructor
@Tag(name = "Course Section")
public class CourseSectionController {

    private final ICourseSectionService courseSectionService;

    @Operation(summary = "Create a course section")
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody CreateCourseSectionRequest request) {
        return ResponseEntity.ok(new MessageResponse(courseSectionService.create(request)));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseSectionResponse>> getAllByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseSectionService.getAllByTeacherId(teacherId));
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<CourseSectionResponse>> getAllBySectionId(@PathVariable Long sectionId) {
        return ResponseEntity.ok(courseSectionService.getAllBySectionId(sectionId));
    }

}
