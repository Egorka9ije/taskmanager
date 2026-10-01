package com.example.taskmanager.controller;

import com.example.taskmanager.dto.response.UserResponse;
import com.example.taskmanager.security.CustomUserDetails;
import com.example.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        return userService.findById(userDetails.getUser().getId());
    }

}
