package org.example.taskmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {
    
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;
}
