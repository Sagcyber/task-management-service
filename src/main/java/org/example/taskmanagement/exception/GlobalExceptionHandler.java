package org.example.taskmanagement.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        
        String message = ex.getBindingResult()
                           .getFieldErrors()
                           .get(0)
                           .getDefaultMessage();
        
        ApiErrorResponse response = new ApiErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        
        return ResponseEntity.badRequest()
                             .body(response);
    }
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                ex.getStatus().value(),
                LocalDateTime.now()
        );
        
        return ResponseEntity
                       .status(ex.getStatus())
                       .body(response);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {
        
        String message = "Data conflict";
        
        if (ex.getMostSpecificCause() != null &&
        ex.getMostSpecificCause().getMessage() != null) {
            
            String causeMessage = ex.getMostSpecificCause().getMessage();
            
            if (causeMessage.contains("users_email_key")) {
                message = "Email already exists";
            } else if (causeMessage.contains("users_username_key")) {
                message = "Username already exists";
            }
        }
        
        ApiErrorResponse response =new ApiErrorResponse(message,
                                                        HttpStatus.CONFLICT.value(),
                                                        LocalDateTime.now());
        
        return ResponseEntity
                       .status(HttpStatus.CONFLICT)
                       .body(response);
    }
}
