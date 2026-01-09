package org.example.taskmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.taskmanagement.model.Category;

@Getter
@Setter
public class TaskRequestDto {
    
    @NotBlank(message = "Title must not be blank")
    private String title;
    
    private String description;
    
    @NotNull(message = "Category must not be null")
    private Category category;
    
    @NotNull(message = "User id must not be null")
    private Long userId;
}
