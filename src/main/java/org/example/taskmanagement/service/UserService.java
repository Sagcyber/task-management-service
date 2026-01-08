package org.example.taskmanagement.service;

import org.example.taskmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    User create(User user);
    
    Optional<User> getById(Long id);
    
    List<User> getAll();
    
    void deleteById(Long id);
}
