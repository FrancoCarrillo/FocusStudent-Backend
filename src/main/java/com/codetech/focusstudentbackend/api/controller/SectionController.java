package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.CreateSectionRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.api.model.responses.SectionResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/section")
@AllArgsConstructor
@Tag(name = "Section")
public class SectionController {

    private final ISectionService sectionService;

    @Operation(summary = "Get all the sections")
    @GetMapping()
    public ResponseEntity<List<SectionResponse>> getAll() {
        return ResponseEntity.ok(sectionService.getAll());
    }

    @Operation(summary = "Get sections by id")
    @GetMapping("/{sectionId}")
    public ResponseEntity<SectionResponse> getById(@PathVariable Long sectionId) {
        return ResponseEntity.ok(sectionService.getById(sectionId));
    }

    @Operation(summary = "Create a section")
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody CreateSectionRequest request) {
        return ResponseEntity.ok(new MessageResponse(sectionService.create(request)));
    }

    @Operation(summary = "Update a section")
    @PutMapping("{sectionId}")
    public ResponseEntity<SectionResponse> updateSection(@PathVariable Long sectionId, @RequestBody UpdateSectionRequest request) {
        return ResponseEntity.ok(sectionService.update(sectionId, request));
    }

    @Operation(summary = "Delete a section")
    @DeleteMapping("{sectionId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long sectionId) {
        return ResponseEntity.ok(new MessageResponse(sectionService.delete(sectionId)));
    }

}
