package com.example.taskmanager.repository;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwnerId(Long ownerId);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByOwnerIdAndStatus(Long ownerId, TaskStatus status);
    boolean existsByIdAndOwnerId(Long id, Long ownerId);
}
