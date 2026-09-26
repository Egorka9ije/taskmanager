package com.example.taskmanager.mapper;


import com.example.taskmanager.domain.Task;
import com.example.taskmanager.dto.request.CreateTaskRequest;
import com.example.taskmanager.dto.response.TaskResponse;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {
    public TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getOwner().getId(),
                task.getCreatedAt()
        );
    }

    public Task toEntity(CreateTaskRequest request){
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setDueDate(request.dueDate());
        return task;
    }
}
