package org.example.taskmanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
    
    @NotBlank(message = "Username must not be blank")
    private String username;
    
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    private String email;
}
