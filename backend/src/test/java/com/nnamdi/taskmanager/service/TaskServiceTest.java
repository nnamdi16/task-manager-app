package com.nnamdi.taskmanager.service;

import com.nnamdi.taskmanager.dto.Response;
import com.nnamdi.taskmanager.dto.UpdateTaskDto;
import com.nnamdi.taskmanager.mock.TestMock;
import com.nnamdi.taskmanager.model.Task;
import com.nnamdi.taskmanager.service.impl.TaskServiceImpl;
import com.nnamdi.taskmanager.utll.AppUtils;
import com.nnamdi.taskmanager.utll.ResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.nnamdi.taskmanager.mock.TestMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    @Mock
    ResponseUtil responseUtil;

    @Mock
    AppUtils appUtils;

    @Mock
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(responseUtil, appUtils);
    }

    @Test
    void testAddTask() {
        Task task = buildTask();
        Response taskResponse = buildResponse(task);
        when(appUtils.generateUUIDString()).thenReturn(TestMock.ID);
        when(responseUtil.getSuccessResponse(buildTask())).thenReturn(taskResponse);
        StepVerifier.create(taskService.createTask(createTaskDto()))
                .expectNext(taskResponse).verifyComplete();
    }


    @Test
    void testGetAllTasks() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Task> taskMap = new HashMap<>();
        taskMap.put(buildTask().getId(), buildTask());
        Response taskResponse = buildResponse(buildTaskList());
        Field taskMapField = TaskServiceImpl.class.getDeclaredField("taskMap");
        taskMapField.setAccessible(true);
        taskMapField.set(taskService, taskMap);
        when(responseUtil.getSuccessResponse(buildTaskList())).thenReturn(taskResponse);
        StepVerifier.create(taskService.getAllTasks())
                .expectNext(taskResponse).verifyComplete();
    }


    @Test
    void testUpdateTask() throws NoSuchFieldException, IllegalAccessException {

        Task existingTask = buildTask();
        Map<String, Task> taskMap = new HashMap<>();
        taskMap.put(buildTask().getId(), existingTask);
        Task updatedTask = buildUpdatedTask();
        Response taskResponse = buildResponse(updatedTask);
        Response errorResponse = buildErrorResponse();

        UpdateTaskDto updateTaskDto = buildUpdateTaskDto();

        Field taskMapField = TaskServiceImpl.class.getDeclaredField("taskMap");
        taskMapField.setAccessible(true);
        taskMapField.set(taskService, taskMap);

        when(responseUtil.getSuccessResponse(updatedTask)).thenReturn(taskResponse);
        when(responseUtil.getErrorResponse(any())).thenReturn(errorResponse);

        StepVerifier.create(taskService.updateTask(TestMock.ID, updateTaskDto))
                .expectNext(taskResponse).verifyComplete();
    }

    @Test
    void testUpdateTask_NotFound() throws NoSuchFieldException, IllegalAccessException {
        String id = "8D19B947443D4C1BB2700337527BC254";
        Task existingTask = buildTask();
        Map<String, Task> taskMap = new HashMap<>();
        taskMap.put(buildTask().getId(), existingTask);
        Task updatedTask = buildUpdatedTask();
        Response taskResponse = buildResponse(updatedTask);
        Response errorResponse = buildErrorResponse();

        UpdateTaskDto updateTaskDto = buildUpdateTaskDto();

        Field taskMapField = TaskServiceImpl.class.getDeclaredField("taskMap");
        taskMapField.setAccessible(true);
        taskMapField.set(taskService, taskMap);

        when(responseUtil.getSuccessResponse(updatedTask)).thenReturn(taskResponse);
        when(responseUtil.getErrorResponse(any())).thenReturn(errorResponse);

        StepVerifier.create(taskService.updateTask(id, updateTaskDto))
                .expectNext(errorResponse).verifyComplete();
    }



















}
