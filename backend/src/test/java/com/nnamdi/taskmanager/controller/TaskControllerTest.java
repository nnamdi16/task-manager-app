package com.nnamdi.taskmanager.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnamdi.taskmanager.dto.Response;
import com.nnamdi.taskmanager.dto.TaskDto;
import com.nnamdi.taskmanager.model.Task;
import com.nnamdi.taskmanager.service.TaskService;
import com.nnamdi.taskmanager.utll.ResponseUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

import static com.nnamdi.taskmanager.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.taskmanager.controller.BaseApiController.TASK_MANAGER;
import static com.nnamdi.taskmanager.mock.TestMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@WebFluxTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TaskService taskService;

    @MockBean
    ResponseUtil responseUtil;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void testCreateTask() throws IOException {


        TaskDto requestDto = createTaskDto();
        Task response = buildTask();

        when(taskService.createTask(requestDto)).thenReturn(Mono.just(buildResponse(response)));


        webTestClient.post()
                .uri(BASE_API_PATH + TASK_MANAGER)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Task.class)
                .consumeWith(task -> {
                    String dataString = new String(Objects.requireNonNull(task.getResponseBodyContent()));
                    Response data;
                    try {
                         data = objectMapper.readValue(dataString, Response.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        assertEquals(objectMapper.writeValueAsString(data.data()), objectMapper.writeValueAsString(response));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });



        Mockito.verify(taskService, times(1)).createTask(requestDto);




    }

    @Test
    void testGetAllTasks() {
        when(taskService.getAllTasks()).thenReturn(Mono.just(buildResponse(buildTaskList())));
        webTestClient.get()
                .uri(BASE_API_PATH + TASK_MANAGER)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Task.class)
                .hasSize(1)
                .consumeWith(task -> {
                    String dataString = new String(Objects.requireNonNull(task.getResponseBodyContent()));
                    Response data;
                    try {
                        data = objectMapper.readValue(dataString,Response.class);

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        assertEquals(objectMapper.writeValueAsString(data.data()), objectMapper.writeValueAsString(buildTaskList()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });

        Mockito.verify(taskService, times(1)).getAllTasks();

    }

    @Test
    void testUpdateTasks() {

        String id = "8D19B947443D4C1BB2700337527BC251";
        when(taskService.updateTask(id, buildUpdateTaskDto())).thenReturn(Mono.just(buildResponse(buildUpdatedTask())));
        webTestClient.put()
                .uri(BASE_API_PATH + TASK_MANAGER + "/" + "8D19B947443D4C1BB2700337527BC251" )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildUpdateTaskDto())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Task.class)
                .consumeWith(task -> {
                    String dataString = new String(Objects.requireNonNull(task.getResponseBodyContent()));
                    Response data;
                    try {
                        data = objectMapper.readValue(dataString,Response.class);

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        assertEquals(objectMapper.writeValueAsString(data.data()), objectMapper.writeValueAsString(buildUpdatedTask()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });

        Mockito.verify(taskService, times(1)).updateTask(id, buildUpdateTaskDto());

    }

}
