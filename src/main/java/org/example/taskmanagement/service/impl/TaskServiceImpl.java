package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.TaskStatus;
import org.example.taskmanagement.repository.TaskRepository;
import org.example.taskmanagement.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    
    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }
    
    @Override
    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }
    
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
    
    @Override
    public List<Task> getByCategory(Category category) {
        return taskRepository.findByCategory(category);
    }
    
    @Override
    public List<Task> getByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
    
    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
