package com.example.taskmanager.dto.response;

import com.example.taskmanager.domain.RoleName;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse( Long id,  String username,  String email,  Set<RoleName> roles,
                            LocalDateTime createdAt) {

}
