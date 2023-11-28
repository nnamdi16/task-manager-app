package com.nnamdi.taskmanager.service;

import com.nnamdi.taskmanager.dto.Response;
import com.nnamdi.taskmanager.dto.TaskDto;
import com.nnamdi.taskmanager.dto.UpdateTaskDto;
import reactor.core.publisher.Mono;

/**
 * Task Service
 *
 */
public interface TaskService {

    /**
     * Creates a task
     * @param taskDto task to be saved
     * @return Mono<Response>
     */
    Mono<Response> createTask(TaskDto taskDto);

    /**
     * Fetches all tasks
     *
     * @return Mono<Response>
     */
    Mono<Response> getAllTasks();

    /**
     * Updates a particular task
     * @param id taskId
     * @param taskDto updated task
     * @return Mono<Response>
     */
    Mono<Response> updateTask(String id, UpdateTaskDto taskDto);


}
