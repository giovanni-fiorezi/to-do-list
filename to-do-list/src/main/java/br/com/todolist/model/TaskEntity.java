package br.com.todolist.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "tasks")
public class TaskEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private String dueDate;
    private LocalDate completed;
    private TaskStatus status;
}
