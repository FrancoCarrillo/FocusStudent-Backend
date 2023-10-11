package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.CreateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateDetectorRequest;
import com.codetech.focusstudentbackend.api.model.responses.DetectorResponse;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.IDetectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/detector")
@AllArgsConstructor
@Tag(name = "Detector")
public class DetectorController {
    private final IDetectorService detectorService;

    @Operation(summary = "Get all the detectors")
    @GetMapping()
    public ResponseEntity<List<DetectorResponse>> getAll() {
        return ResponseEntity.ok(detectorService.getAll());
    }

    @Operation(summary = "Get detector by ID")
    @GetMapping("/{detectorId}")
    public ResponseEntity<DetectorResponse> getById(@PathVariable Long detectorId) {
        return ResponseEntity.ok(detectorService.getById(detectorId));
    }

    @Operation(summary = "Create a detector")
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody CreateDetectorRequest request) {
        return ResponseEntity.ok(new MessageResponse(detectorService.create(request)));
    }

    @Operation(summary = "Update a detector")
    @PutMapping("{detectorId}")
    public ResponseEntity<DetectorResponse> update(@PathVariable Long detectorId, @RequestBody UpdateDetectorRequest request) {
        return ResponseEntity.ok(detectorService.update(detectorId, request));
    }

    @Operation(summary = "Delete a detector")
    @DeleteMapping("{detectorId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long detectorId) {
        return ResponseEntity.ok(new MessageResponse(detectorService.delete(detectorId)));
    }
}
