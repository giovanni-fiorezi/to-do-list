package br.com.todolist.service;

import br.com.todolist.converter.TaskDtoConverter;
import br.com.todolist.converter.TaskEntityConverter;
import br.com.todolist.dto.TaskDto;
import br.com.todolist.model.TaskEntity;
import br.com.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDto create(TaskDto taskDto) {
        try {
            TaskEntity taskEntity = taskRepository.save(TaskEntityConverter.fromDto(taskDto));
            return TaskDtoConverter.fromEntity(taskEntity);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao salvar uma nova tarefa.");
        }
    }

    public TaskDto update(String id) {
        try {
            Optional<TaskEntity> idTask = taskRepository.findById(id);
            if(idTask.isEmpty()) {
                throw new Exception("Não existe o id informado");
            }
            TaskEntity taskEntity = taskRepository.save(idTask.get());
            return TaskDtoConverter.fromEntity(taskEntity);
        } catch(Exception e) {
            throw new RuntimeException("Ocorreu um erro ao buscar o id da tarefa");
        }
    }

    public void delete(String id) {
        try {
            Optional<TaskEntity> idTask = taskRepository.findById(id);
            if(idTask.isEmpty()) {
                throw new Exception("Não existe o id informado");
            }
            taskRepository.delete(idTask.get());
        } catch(Exception e) {
            throw new RuntimeException("Ocorreu um erro ao buscar o id da tarefa");
        }
    }

    public TaskDto findById(String id) {
        try {
            Optional<TaskEntity> findId = taskRepository.findById(id);
            if (findId.isEmpty()) {
                throw new Exception("Não existe o id informado");
            }
            TaskEntity entity = findId.get();
            return TaskDtoConverter.fromEntity(entity);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao buscar o id da tarefa");
        }
    }

    public List<TaskDto> findByTitle(String title) {
        List<TaskEntity> titleContaining = taskRepository.findByTitleContaining(title);
        if(titleContaining.isEmpty()) {
            throw new RuntimeException("Titulo nao encontrado.");
        }
        return titleContaining.stream()
                .map(TaskDtoConverter::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TaskDto> findAll() {
        try {
            return taskRepository.findAll()
                    .stream()
                    .map(TaskDtoConverter::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao listar todas as tarefas.");
        }
    }
}