package br.com.todolist.repository;

import br.com.todolist.model.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findByTitleContaining(String title);
}
