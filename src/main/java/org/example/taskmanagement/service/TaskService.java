package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.dto.response.TaskResponseDto;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.TaskStatus;

import java.util.List;

public interface TaskService {
    
    TaskResponseDto create(TaskRequestDto requestDto);
    
    TaskResponseDto getById(Long id);
    
    List<TaskResponseDto> getAll();
    
    List<TaskResponseDto> getByCategory(Category category);
    
    List<TaskResponseDto> getByStatus(TaskStatus status);
    
    void deleteById(Long id);
}
