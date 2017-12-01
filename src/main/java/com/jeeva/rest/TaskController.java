package com.jeeva.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CustomSpringEventPublisher customSpringEventPublisher;

    @GetMapping("tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        customSpringEventPublisher.publishCustomSpringEvent("TASK_CREATED");
        return taskRepository.save(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId) {
        Task task = taskRepository.findOne(taskId);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateNote(@PathVariable(value = "id") Long taskId,
                                           @Valid @RequestBody Task noteDetails) {
        Task task = taskRepository.findOne(taskId);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        task.setName(noteDetails.getName());
        task.setDescription(noteDetails.getDescription());

        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "id") Long taskId) {
        Task task = taskRepository.findOne(taskId);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }
}
