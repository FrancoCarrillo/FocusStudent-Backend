package com.codetech.focusstudentbackend.api.controller;

import com.codetech.focusstudentbackend.api.model.requests.ChangePasswordRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateUserRequest;
import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;
import com.codetech.focusstudentbackend.api.model.responses.MessageResponse;
import com.codetech.focusstudentbackend.api.model.responses.UserResponse;
import com.codetech.focusstudentbackend.infraestructure.interfaces.ISecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@Tag(name = "User")
public class UserController {

    private final ISecurityService securityService;

    @Operation(summary = "Get all users by lastnames and role")
    @GetMapping("rol/{role}/lastNames/{lastNames}")
    public ResponseEntity<List<UserResponse>> getAllByRoleAndLastNames(@PathVariable String role, @PathVariable String lastNames) {
        return ResponseEntity.ok(securityService.getAllUsersByLastNameAndRol(lastNames, role));
    }

    @Operation(summary = "Login in system")
    @PostMapping("auth/login")
    public ResponseEntity<LogInResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(securityService.login(loginRequest));
    }

    @Operation(summary = "Register in system")
    @PostMapping("auth/register")
    public ResponseEntity<MessageResponse> register(@RequestBody CreateUserRequest signUpRequest) {
        return ResponseEntity.ok(new MessageResponse(securityService.register(signUpRequest)));
    }

    @Operation(summary = "Update user")
    @PutMapping("{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(securityService.update(userId, request));
    }

    @Operation(summary = "Change password")
    @PutMapping("{userId}/password")
    public ResponseEntity<MessageResponse> changePassword(@PathVariable Long userId, @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(new MessageResponse(securityService.changePassword(userId, request.getPassword())));
    }

    @Operation(summary = "Delete teacher or studen")
    @DeleteMapping("{userId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long userId) {
        return ResponseEntity.ok(new MessageResponse(securityService.delete(userId)));
    }

}
