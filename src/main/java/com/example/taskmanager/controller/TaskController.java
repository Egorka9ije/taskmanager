package com.example.taskmanager.controller;

import com.example.taskmanager.domain.TaskStatus;
import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import com.example.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestBody CreateTaskRequest request, @RequestParam Long ownerId) {
        return taskService.create(request, ownerId);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(@RequestParam Long ownerId) {
        return taskService.findAllByOwner(ownerId);
    }

    @PatchMapping("/{id}/status")
    public TaskResponse changeStatus(@PathVariable Long id,
                                     @RequestParam TaskStatus status,
                                     @RequestParam Long ownerId){
        return taskService.updateStatus(id, status, ownerId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id, @RequestParam Long ownerId) {
        taskService.delete(id, ownerId);
    }


}
