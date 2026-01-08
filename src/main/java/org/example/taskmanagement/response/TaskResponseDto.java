package org.example.taskmanagement.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDto {
    
    private Long id;
    private String title;
    private String description;
    private String status;
    private String categoryName;
    
}
