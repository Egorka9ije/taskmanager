package com.example.taskmanager.controller;

import com.example.taskmanager.dto.response.UserResponse;
import com.example.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/by-username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
