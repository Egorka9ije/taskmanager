package com.example.taskmanager.controller;

import com.example.taskmanager.domain.TaskStatus;
import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import com.example.taskmanager.security.CustomUserDetails;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request,
                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        return taskService.create(request, userDetails.getUser().getId());
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        return taskService.findById(id, userDetails.getUser().getId());
    }

    @GetMapping
    public List<TaskResponse> getAllTasks( @AuthenticationPrincipal CustomUserDetails userDetails) {
        return taskService.findAllByOwner(userDetails.getUser().getId());
    }

    @PatchMapping("/{id}/status")
    public TaskResponse changeStatus(@PathVariable Long id,
                                     @RequestParam TaskStatus status,
                                     @AuthenticationPrincipal CustomUserDetails userDetails){
        return taskService.updateStatus(id, status, userDetails.getUser().getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        taskService.delete(id, userDetails.getUser().getId());
    }


}
