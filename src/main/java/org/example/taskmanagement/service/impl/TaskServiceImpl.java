package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.dto.request.TaskUpdateRequestDto;
import org.example.taskmanagement.dto.response.TaskResponseDto;
import org.example.taskmanagement.exception.NotFoundException;
import org.example.taskmanagement.mapper.TaskMapper;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.TaskStatus;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.TaskRepository;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    
    @Override
    public TaskResponseDto create(TaskRequestDto requestDto) {
        log.info("Creating task for userId={}, category={}",
                 requestDto.getUserId(),
                 requestDto.getCategory());
        
        User user = userRepository.findById(requestDto.getUserId())
                                  .orElseThrow(() -> new NotFoundException("User not found"));
        
        Task task = taskMapper.toEntity(requestDto);
        
        task.setStatus(TaskStatus.NEW);
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);
        
        Task savedTask = taskRepository.save(task);
        
        log.info("Task created with id={}", savedTask.getId());
        
        return taskMapper.toDto(savedTask);
    }
    
    @Override
    public TaskResponseDto getById(Long id) {
        
        log.info("Fetching task by id={}", id);
        
        Task task = taskRepository.findById(id)
                                  .orElseThrow(() -> new NotFoundException("Task not found"));
        
        log.info("Task found with status={}", task.getStatus());
        
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
        
        log.info("Fetching tasks by category={}", category);
        
        return taskRepository.findByCategory(category)
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }
    
    @Override
    public List<TaskResponseDto> getByStatus(TaskStatus status) {
        
        log.info("Fetching tasks by status={}", status);
        
        return taskRepository.findByStatus(status)
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }
    
    public TaskResponseDto update(Long id, TaskUpdateRequestDto dto) {
        log.info("Updating task id={}", id);
        
        Task task = taskRepository.findById(id)
                            .orElseThrow(()-> new NotFoundException("Task not found"));
        
        taskMapper.updateEntity(task, dto);
        
        Task updated = taskRepository.save(task);
        
        log.info("Task updated id={}, status={}", updated.getId(), updated.getStatus());
        
        return taskMapper.toDto(updated);
    }
    
    @Override
    public void deleteById(Long id) {
        
        log.info("Deleting task with id={}", id);
        
        taskRepository.deleteById(id);
        
        log.info("Task deleted with id={}", id);
    }
}
