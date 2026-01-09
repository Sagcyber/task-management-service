package org.example.taskmanagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                                      .getFieldErrors()
                                      .get(0)
                                      .getDefaultMessage();
        
        ApiErrorResponse response = new ApiErrorResponse(errorMessage, 400);
        
        return ResponseEntity.badRequest().body(response);
    }
}
