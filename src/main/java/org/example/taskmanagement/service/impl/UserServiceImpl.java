package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskmanagement.dto.request.UserRequestDto;
import org.example.taskmanagement.dto.request.UserUpdateRequestDto;
import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.exception.NotFoundException;
import org.example.taskmanagement.mapper.UserMapper;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Override
    public UserResponseDto create(UserRequestDto dto) {
        log.info("Creating user with username={}", dto.getUsername());
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        log.info("User created with id={}", saved.getId());
        return userMapper.toDto(saved);
    }
    
    @Override
    public UserResponseDto getById(Long id) {
        log.info("Getting user with id={}", id);
        User user = userRepository.findById(id)
                            .orElseThrow(() -> {
                                log.warn("User not found with id={} ", id);
                                return new NotFoundException("User not found");
                            });
        log.info("User found: {}", user.getUsername());
        return userMapper.toDto(user);
    }
    
    @Override
    public List<UserResponseDto> getAll() {
        log.info("Getting all users");
        List<UserResponseDto> list = userRepository.findAll()
                                                   .stream()
                                                   .map(userMapper::toDto)
                                                   .toList();
        log.info("Users found: {}", list.size());
        return list;
    }
    
    @Override
    public void deleteById(Long id) {
        log.info("Deleting user with id={}", id);
        userRepository.deleteById(id);
        log.info("User deleted with id={}", id);
    }
    
    @Override
    public UserResponseDto update(Long id, UserUpdateRequestDto dto) {
        log.info("Updating user id={}", id);
        
        User user = userRepository.findById(id)
                            .orElseThrow(() -> {
                                log.warn("User not found for update, id={}", id);
                                return new NotFoundException("User not found");
                            });
        
        userMapper.updateEntity(user, dto);
        
        User updated = userRepository.save(user);
        
        log.info("User updated id={}, username={}", updated.getId(), updated.getUsername());
        
        return userMapper.toDto(updated);
    }
}
