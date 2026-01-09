package org.example.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.dto.response.TaskResponseDto;
import org.example.taskmanagement.model.Category;
import org.example.taskmanagement.model.TaskStatus;
import org.example.taskmanagement.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    @PostMapping
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        return taskService.create(requestDto);
    }
    
    @GetMapping("/{id}")
    public TaskResponseDto getById(@PathVariable Long id) {
        return taskService.getById(id);
    }
    
    @GetMapping
    public List<TaskResponseDto> getAll() {
        return taskService.getAll();
    }
    
    @GetMapping("/category/{category}")
    public List<TaskResponseDto> getByCategory(@PathVariable Category category) {
        return taskService.getByCategory(category);
    }
    
    @GetMapping("/status/{status}")
    public List<TaskResponseDto> getByStatus(@PathVariable TaskStatus status) {
        return taskService.getByStatus(status);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteById(id);
    }
    
}
