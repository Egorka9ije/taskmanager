package com.example.taskmanager.dto.response;

import com.example.taskmanager.domain.TaskPriority;
import com.example.taskmanager.domain.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, String description,
                           TaskStatus status, TaskPriority priority,
                           LocalDateTime dueDate, Long ownerId,
                           LocalDateTime createdAt) {
}
