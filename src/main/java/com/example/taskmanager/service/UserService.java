package com.example.taskmanager.service;

import com.example.taskmanager.dto.request.RegisterRequest;
import com.example.taskmanager.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);
    UserResponse findById(Long id);
    UserResponse findByUsername(String username);
}
