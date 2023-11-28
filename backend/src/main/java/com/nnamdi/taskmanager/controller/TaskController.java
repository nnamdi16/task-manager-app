package com.nnamdi.taskmanager.controller;

import com.nnamdi.taskmanager.dto.Response;
import com.nnamdi.taskmanager.dto.TaskDto;
import com.nnamdi.taskmanager.dto.UpdateTaskDto;
import com.nnamdi.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.nnamdi.taskmanager.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.taskmanager.controller.BaseApiController.TASK_MANAGER;

@RestController
@Slf4j
@RequestMapping(BASE_API_PATH + TASK_MANAGER)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<ResponseEntity<Response>> createTask(@RequestBody @Valid TaskDto requestDto) {
        return taskService.createTask(requestDto).map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Mono<ResponseEntity<Response>> getAllTasks() {
        return taskService.getAllTasks().map(ResponseEntity::ok);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{taskId}")
    public Mono<ResponseEntity<Response>> updateTask(@PathVariable("taskId") String taskId, @RequestBody @Valid UpdateTaskDto updateTaskDto) {
        return taskService.updateTask(taskId, updateTaskDto).map(ResponseEntity::ok);
    }
}
