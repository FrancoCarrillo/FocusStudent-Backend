package com.codetech.focusstudentbackend.infraestructure.interfaces;

import com.codetech.focusstudentbackend.api.model.requests.LoginRequest;
import com.codetech.focusstudentbackend.api.model.requests.RegisterUserRequest;
import com.codetech.focusstudentbackend.api.model.responses.LogInResponse;

public interface ISecurityService {

    LogInResponse login(LoginRequest loginRequest);

    String register(RegisterUserRequest registerUserRequest);

}