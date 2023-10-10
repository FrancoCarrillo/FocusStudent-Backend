package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.CreateUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;

public interface ISecurityService {

    LogInResponse login(LoginRequest loginRequest);

    String register(CreateUserRequest createUserRequest);

}