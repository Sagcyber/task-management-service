package org.example.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TaskManagementServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementServiceApplication.class, args);
    }
    
}
