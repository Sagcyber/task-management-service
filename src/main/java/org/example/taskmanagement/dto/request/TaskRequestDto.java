package org.example.taskmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.taskmanagement.model.Category;

@Getter
@Setter
public class TaskRequestDto {

    private String title;
    private String description;
    private Category category;
    private Long userId;
    
}
