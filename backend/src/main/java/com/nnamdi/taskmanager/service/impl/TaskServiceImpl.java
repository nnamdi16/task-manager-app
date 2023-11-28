package com.nnamdi.taskmanager.service.impl;

import com.nnamdi.taskmanager.dto.Error;
import com.nnamdi.taskmanager.dto.*;
import com.nnamdi.taskmanager.model.Task;
import com.nnamdi.taskmanager.service.TaskService;
import com.nnamdi.taskmanager.utll.AppUtils;
import com.nnamdi.taskmanager.utll.ConstantsUtil;
import com.nnamdi.taskmanager.utll.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final ResponseUtil responseUtil;
    private final AppUtils appUtils;


    Map<String, Task> taskMap = new HashMap<>();

    @Autowired
    public TaskServiceImpl(ResponseUtil responseUtil, AppUtils appUtils) {
        this.responseUtil = responseUtil;
        this.appUtils = appUtils;
    }


    @Override
    public Mono<Response> createTask(TaskDto taskDto) {
        Task task = builderTask(taskDto);
        taskMap.put(task.getId(), task);
        return Mono.just(task).map(responseUtil::getSuccessResponse);
    }

    @Override
    public Mono<Response> getAllTasks() {
        return Flux.fromIterable(taskMap.values()).collectList().map(responseUtil::getSuccessResponse);
    }

    @Override
    public Mono<Response> updateTask(String id, UpdateTaskDto taskDto) {
        return Mono.justOrEmpty(findTaskById(id))
                .flatMap(existingTask -> {
                    Task updatedTask = updateTask(taskDto, existingTask);
                    taskMap.put(existingTask.getId(), updatedTask);
                    return Mono.just(updatedTask);
                })
                .map(responseUtil::getSuccessResponse)
                .switchIfEmpty(Mono.just(responseUtil.getErrorResponse(new Error(ResponseCodes.NOT_FOUND, ConstantsUtil.NOT_FOUND, "Task not found"))));
    }

    private Task builderTask(TaskDto taskDto) {
        return Task.builder()
                .id(appUtils.generateUUIDString())
                .content(taskDto.getContent())
                .isCompleted(false)
                .build();
    }

    private Task updateTask(UpdateTaskDto taskDto, Task task) {


        String description = StringUtils.defaultIfBlank(taskDto.getContent(), StringUtils.defaultString(task.getContent()));
        boolean isCompleted = taskDto.isCompleted() || task.isCompleted();
        return Task.builder()
                .content(description)
                .isCompleted(isCompleted)
                .id(task.getId())
                .build();
    }

    private Optional<Task> findTaskById(String id) {
        return Optional.ofNullable(taskMap.get(id));
    }
}
