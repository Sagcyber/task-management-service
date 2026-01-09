package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.dto.response.TaskResponseDto;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.TaskStatus;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.TaskRepository;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    
    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                                  .orElseThrow(() -> new RuntimeException("User not found"));
        
        Task task = taskMapper.toEntity(requestDto);
        task.setUser(user);
        
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }
    
    @Override
    public TaskResponseDto getById(Long id) {
        Task task = taskRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.toDto(task);
    }
    
    @Override
    public List<TaskResponseDto> getAll() {
        return taskRepository.findAll()
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }
    
    @Override
    public List<TaskResponseDto> getByCategory(Category category) {
        return taskRepository.findByCategory(category)
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }
    
    @Override
    public List<TaskResponseDto> getByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status)
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }
    
    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
