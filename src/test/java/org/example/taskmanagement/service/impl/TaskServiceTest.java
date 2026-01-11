package org.example.taskmanagement.service.impl;

import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.dto.response.TaskResponseDto;
import org.example.taskmanagement.exception.NotFoundException;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.TaskRepository;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceTest {
    
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskMapper taskMapper;
    
    private TaskService taskService;
    
    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        taskMapper = new TaskMapper();
        
        taskService = new TaskServiceImpl(
                taskRepository,
                userRepository,
                taskMapper
        );
    }
    
    @Test
    void create_shouldCreateTaskSuccessfully() {
        // given
        TaskRequestDto dto = new TaskRequestDto();
        dto.setTitle("Test task");
        dto.setDescription("Description");
        dto.setCategory(Category.WORK);
        dto.setUserId(1L);
        
        User user = new User();
        user.setId(1L);
        user.setUsername("fabi");
        
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> {
                    Task task = invocation.getArgument(0);
                    task.setId(10L);
                    return task;
                });
        
        // when
        TaskResponseDto response = taskService.create(dto);
        
        // then
        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Test task", response.getTitle());
        assertEquals(Category.WORK, response.getCategory());
        
        verify(userRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }
    
    @Test
    void create_shouldThrowException_whenUserNotFound() {
        // given
        TaskRequestDto dto = new TaskRequestDto();
        dto.setTitle("Task");
        dto.setCategory(Category.HOME);
        dto.setUserId(99L);
        
        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());
        
        // when + then
        assertThrows(NotFoundException.class,
                     () -> taskService.create(dto));
        
        verify(taskRepository, never()).save(any());
    }
}
