package org.example.taskmanagement.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.taskmanagement.model.Category;

@Getter
@Setter
public class TaskResponseDto {
    
    private Long id;
    private String title;
    private String description;
    private String status;
    private Category category;
    
}
