package org.example.taskmanagement.service.impl;

import org.example.taskmanagement.dto.response.UserResponseDto;
import org.example.taskmanagement.model.User;
import org.example.taskmanagement.repository.UserRepository;
import org.example.taskmanagement.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableCaching
class UserServiceCacheTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CacheManager cacheManager;
    
    @TestConfiguration
    static class TestConfig {
        
        @Bean
        UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }
    }
    
    @Test
    void getById_shouldUseCache() {
        
        User user = new User();
        user.setId(1L);
        user.setUsername("fabi");
        user.setEmail("fabi@test.com");
        
        Mockito.when(userRepository.findById(1L))
               .thenReturn(Optional.of(user));
        
        UserResponseDto first = userService.getById(1L);
        UserResponseDto second = userService.getById(1L);
        
        Mockito.verify(userRepository, Mockito.times(1))
               .findById(1L);
        
        assertEquals(first.getId(), second.getId());
    }
}

