package br.com.todolist.converter;

import br.com.todolist.dto.TaskDto;
import br.com.todolist.model.TaskEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskEntityConverter {

    public static TaskEntity fromDto(TaskDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime agora = LocalDateTime.now();
        return TaskEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(agora.format(formatter)) /* pegar a data e hora atual que foi salvo */
                .completed(dto.getCompleted())
                .build();
    }
}
