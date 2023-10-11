package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.CreateSectionRequest;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/section")
@AllArgsConstructor
@Tag(name = "Section")
public class SectionController {

    private final ISectionService sectionService;

    @Operation(summary = "Create a section")
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody CreateSectionRequest request) {
        return ResponseEntity.ok(new MessageResponse(sectionService.create(request)));
    }

}
