package com.example.taskmanager.service;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.User;
import com.example.taskmanager.dto.response.TaskResponse;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void findById_whenOwnerMatches_returnsTaskResponse() {
        // Arrange
        User owner = new User();
        owner.setId(1L);

        Task task = new Task();
        task.setId(10L);
        task.setTitle("Купить молоко");
        task.setOwner(owner);

        TaskResponse expectedResponse = new TaskResponse(
                10L, "Купить молоко", null, null, null, null, 1L, null);

        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));
        when(taskMapper.toResponse(task)).thenReturn(expectedResponse);

        // Act
        TaskResponse actual = taskService.findById(10L, 1L);

        // Assert
        assertThat(actual).isEqualTo(expectedResponse);
        verify(taskRepository).findById(10L);
        verify(taskMapper).toResponse(task);
    }

    @Test
    void findById_whenTaskNotFound_throwsTaskNotFoundException() {
        // Arrange
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> taskService.findById(999L, 1L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task not found");
    }

    @Test
    void findById_whenUserIsNotOwner_throwsTaskNotFoundException() {
        // Arrange
        User owner = new User();
        owner.setId(2L);

        Task task = new Task();
        task.setId(10L);
        task.setOwner(owner);

        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        // Act + Assert
        assertThatThrownBy(() -> taskService.findById(10L, 1L))  // ownerId=1, а у задачи owner=2
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task not found");
    }
}