package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.request.UserRequestDto;
import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    UserResponseDto create(UserRequestDto dto);
    
    UserResponseDto getById(Long id);
    
    List<UserResponseDto> getAll();
    
    void deleteById(Long id);
}
