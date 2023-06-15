package br.com.todolist.service;

import br.com.todolist.converter.TaskDtoConverter;
import br.com.todolist.converter.TaskEntityConverter;
import br.com.todolist.dto.TaskDto;
import br.com.todolist.exception.ListException;
import br.com.todolist.model.TaskEntity;
import br.com.todolist.model.TaskStatus;
import br.com.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private static final String ACTION_ERROR_ID = "Ocorreu um erro ao buscar o id da tarefa";
    private static final String ACTION_ERROR_NOT_EXISTS_ID = "Não existe o id informado";

    public TaskDto create(TaskDto taskDto) {
        try {
            TaskEntity taskEntity = taskRepository.save(TaskEntityConverter.fromDto(taskDto));
            taskEntity.setStatus(TaskStatus.NAO_INICIADA);
            return TaskDtoConverter.fromEntity(taskEntity);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao salvar uma nova tarefa.");
        }
    }

    public TaskDto update(TaskDto dto) {
        try {
            Optional<TaskEntity> idTask = taskRepository.findById(dto.getId());
            if (idTask.isEmpty()) {
                throw new RuntimeException(ACTION_ERROR_NOT_EXISTS_ID);
            }
            TaskEntity save = taskRepository.save(TaskEntityConverter.fromDto(dto));
            return TaskDtoConverter.fromEntity(save);
        } catch(Exception e) {
            throw new RuntimeException(ACTION_ERROR_ID);
        }
    }

    public void delete(String id) {
        try {
            Optional<TaskEntity> idTask = taskRepository.findById(id);
            if(idTask.isEmpty()) {
                throw new Exception(ACTION_ERROR_NOT_EXISTS_ID);
            }
            taskRepository.delete(idTask.get());
        } catch(Exception e) {
            throw new RuntimeException(ACTION_ERROR_ID);
        }
    }

    public TaskDto findById(String id) {
        try {
            Optional<TaskEntity> findId = taskRepository.findById(id);
            if (findId.isEmpty()) {
                throw new Exception(ACTION_ERROR_NOT_EXISTS_ID);
            }
            TaskEntity entity = findId.get();
            return TaskDtoConverter.fromEntity(entity);
        } catch (Exception e) {
            throw new RuntimeException(ACTION_ERROR_ID);
        }
    }

    public List<TaskDto> findByTitle(String title) {
        List<TaskEntity> titleContaining = taskRepository.findByTitleContaining(title);
        if(titleContaining.isEmpty()) {
            throw new ListException(String.format("Titulo %s nao encontrado.", title));
        }
        return titleContaining.stream().map(TaskDtoConverter::fromEntity).toList();
    }

    public List<TaskDto> findAll() {
        try {
            return taskRepository.findAll().stream().map(TaskDtoConverter::fromEntity)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao listar todas as tarefas.");
        }
    }
}