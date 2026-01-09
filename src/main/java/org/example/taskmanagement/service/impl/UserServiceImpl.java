package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.dto.request.UserRequestDto;
import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.exception.NotFoundException;
import org.example.taskmanagement.mapper.UserMapper;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Override
    public UserResponseDto create(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }
    
    @Override
    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toDto(user);
    }
    
    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                       .stream()
                       .map(userMapper::toDto)
                       .toList();
    }
    
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
