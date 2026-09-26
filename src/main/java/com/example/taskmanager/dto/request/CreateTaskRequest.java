package com.example.taskmanager.dto.request;

import com.example.taskmanager.domain.TaskPriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        @NotBlank
        @Size(max = 255)
        String title,

        @Size(max = 5000)
        String description,

        @NotNull
        TaskPriority priority,

        @Future
        LocalDateTime dueDate) {

}
