package com.example.taskmanager.controller;

import com.example.taskmanager.dto.request.RegisterRequest;
import com.example.taskmanager.dto.response.UserResponse;
import com.example.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/by-username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
