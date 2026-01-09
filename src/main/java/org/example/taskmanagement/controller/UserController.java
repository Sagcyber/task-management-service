package org.example.taskmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taskmanagement.dto.request.UserRequestDto;
import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    public UserResponseDto create(@Valid @RequestBody
                                  UserRequestDto dto) {
        return userService.create(dto);
    }
    
    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }
    
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }
    
}
