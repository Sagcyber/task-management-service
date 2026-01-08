package org.example.taskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
