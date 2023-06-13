package br.com.todolist.resource;

import br.com.todolist.dto.TaskDto;
import br.com.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/tasks")
@RestController
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> save(@RequestBody TaskDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(dto));
    }

    @PutMapping
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto dto) {
        TaskDto taskDto = taskService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tarefa excluida com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping("/find-by-title")
    public ResponseEntity<List<TaskDto>> findByTitle(@RequestParam (value = "title") String title) {
        List<TaskDto> byTitle = taskService.findByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(byTitle);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<TaskDto> findById(@RequestParam (value = "id") String id) {
        TaskDto byId = taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }

}
