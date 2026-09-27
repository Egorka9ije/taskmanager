package com.example.taskmanager.controller;

import com.example.taskmanager.dto.request.RegisterRequest;
import com.example.taskmanager.dto.response.UserResponse;
import com.example.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }
}
