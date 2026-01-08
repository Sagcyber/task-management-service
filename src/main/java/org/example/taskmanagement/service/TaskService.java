package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    
    Task create(TaskRequestDto requestDto);
    
    Optional<Task> getById(Long id);
    
    List<Task> getAll();
    
    List<Task> getByCategory(Category category);
    
    List<Task> getByStatus(TaskStatus status);
    
    void deleteById(Long id);

}
