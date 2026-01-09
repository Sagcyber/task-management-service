package org.example.taskmanagement.mapper;

import org.example.taskmanagement.dto.request.UserRequestDto;
import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    
    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }
    
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
