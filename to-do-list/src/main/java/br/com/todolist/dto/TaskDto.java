package br.com.todolist.dto;

import br.com.todolist.model.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskDto {

    private String id;
    private String title;
    private String description;
    private String dueDate;
    private LocalDate completed;
    private TaskStatus status;
}
