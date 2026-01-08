package org.example.taskmanagement.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDto {

    private String title;
    private String description;
    private Long categoryId;
    private Long userId;
    
}
