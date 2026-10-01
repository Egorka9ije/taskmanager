package com.example.taskmanager.service;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskStatus;
import com.example.taskmanager.domain.User;
import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.exception.UserNotFoundException;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponse create(CreateTaskRequest request, Long ownerId){
        User user = userRepository.findById(ownerId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Task task = taskMapper.toEntity(request);
        task.setOwner(user);
        Task saved = taskRepository.save(task);
        return taskMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse findById(Long id, Long ownerId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        if (!task.getOwner().getId().equals(ownerId)){
            throw new TaskNotFoundException("Task not found");
        }
        return taskMapper.toResponse(task);
    }

    @Transactional
    @Override
    public TaskResponse updateStatus(Long taskId, TaskStatus newStatus, Long ownerId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        if (!task.getOwner().getId().equals(ownerId)) {
            throw new TaskNotFoundException("User is not the owner of this task");
        }
        task.setStatus(newStatus);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Transactional
    @Override
    public void delete(Long taskId, Long ownerId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        if (!task.getOwner().getId().equals(ownerId)) {
            throw new TaskNotFoundException("Task not found");
        }
        taskRepository.delete(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findAllByOwner(Long ownerId) {
        return taskRepository.findByOwnerId(ownerId).stream()
                .map(taskMapper::toResponse)
                .toList();
    }
}
