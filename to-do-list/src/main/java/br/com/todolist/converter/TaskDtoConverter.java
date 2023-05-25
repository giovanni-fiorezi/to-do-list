package br.com.todolist.converter;

import br.com.todolist.dto.TaskDto;
import br.com.todolist.model.TaskEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskDtoConverter {

    public static TaskDto fromEntity(TaskEntity entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime agora = LocalDateTime.now();
        return TaskDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .dueDate(agora.format(formatter))
                .completed(entity.getCompleted())
                .build();
    }
}
