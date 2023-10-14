package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateUserRequest;
import com.codetech.focusstudentbackend.api.model.requests.UpdateUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;
import com.codetech.focusstudentbackend.api.model.responses.UserResponse;

import java.util.List;

public interface ISecurityService {

    LogInResponse login(LoginRequest loginRequest);

    String register(CreateUserRequest createUserRequest);

    UserResponse update(Long userId, UpdateUserRequest request);

    List<UserResponse> getAllUsersByLastNameAndRol(String lastName, String rol);

    String changePassword(Long userId, String password);
}