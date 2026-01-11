package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskmanagement.dto.request.UserRequestDto;
import org.example.taskmanagement.dto.request.UserUpdateRequestDto;
import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.exception.ConflictException;
import org.example.taskmanagement.exception.NotFoundException;
import org.example.taskmanagement.mapper.UserMapper;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.TaskRepository;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private final UserMapper userMapper;
    
    private final TaskRepository taskRepository;
    
    @Override
    public UserResponseDto create(UserRequestDto dto) {
        log.info("Creating user with username={}", dto.getUsername());
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        log.info("User created with id={}", saved.getId());
        return userMapper.toDto(saved);
    }
    
    @Override
    @Cacheable(value = "users", key = "#id")
    public UserResponseDto getById(Long id) {
        log.info("Getting user from DB, id={}", id);
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
    @CacheEvict(value = "users", key = "#id")
    public UserResponseDto update(Long id, UserUpdateRequestDto dto) {
        log.info("Updating user id={}, evicting cache", id);
        
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
    
    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteById(Long id) {
        log.info("Deleting user with id={}, evicting cache", id);
        
        User user = userRepository.findById(id)
                                          .orElseThrow(() -> {
                                              log.warn("User not found for delete, id={}", id);
                                              return new NotFoundException("User not found");
                                          });
        
        boolean hasTasks = taskRepository.existsByUserId(user.getId());
        
        if(hasTasks) {
            log.warn("Cannot delete user id={} because tasks exist", id);
            throw new ConflictException("Cannot delete user with existing tasks");
        }
        
        userRepository.delete(user);
        log.info("User deleted with id={}", id);
    }
}
