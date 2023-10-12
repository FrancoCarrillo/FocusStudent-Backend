package com.codetech.focusstudentbackend.api.mapping;

import com.codetech.focusstudentbackend.api.model.responses.UserResponse;
import com.codetech.focusstudentbackend.core.entities.User;
import com.codetech.focusstudentbackend.utils.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public UserResponse toResponse(User model) {
        return mapper.map(model, UserResponse.class);
    }

    public List<UserResponse> modelToList(List<User> modelList) {
        return mapper.mapList(modelList, UserResponse.class);
    }
}
