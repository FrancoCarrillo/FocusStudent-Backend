package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@Tag(name = "User")
public class UserController {

    @Operation(summary = "Login in system")
    @PostMapping("auth/login")
    public ResponseEntity<LogInResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(securityService.login(loginRequest));
    }

    private final ISecurityService securityService;

    @Operation(summary = "Register in system")
    @PostMapping("auth/register")
    public ResponseEntity<MessageResponse> register(@RequestBody CreateUserRequest signUpRequest) {
        return ResponseEntity.ok(new MessageResponse(securityService.register(signUpRequest)));
    }

}
