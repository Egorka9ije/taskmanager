package com.example.taskmanager.dto.request;

import com.example.taskmanager.domain.TaskPriority;

import java.time.LocalDateTime;

public record CreateTaskRequest(String title, String description, TaskPriority priority, LocalDateTime dueDate) {

}
