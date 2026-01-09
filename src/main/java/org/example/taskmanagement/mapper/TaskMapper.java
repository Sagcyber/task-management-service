package org.example.taskmanagement.mapper;

import org.example.taskmanagement.dto.request.TaskRequestDto;
import org.example.taskmanagement.dto.response.TaskResponseDto;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {
    
    public Task toEntity(TaskRequestDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(dto.getCategory());
        return task;
    }
    
    public TaskResponseDto toDto(Task task) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus().name());
        dto.setCategory(task.getCategory());
        return dto;
    }
}
