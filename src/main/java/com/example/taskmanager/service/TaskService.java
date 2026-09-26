package com.example.taskmanager.service;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskStatus;
import com.example.taskmanager.domain.User;
import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import java.util.List;

public interface TaskService {
    TaskResponse create(CreateTaskRequest request, Long ownerId);
    TaskResponse findById(Long id);
    List<TaskResponse> findAllByOwner(Long ownerId);
    TaskResponse updateStatus(Long taskId, TaskStatus newStatus, Long ownerId);
    void delete(Long taskId, Long ownerId);

}
