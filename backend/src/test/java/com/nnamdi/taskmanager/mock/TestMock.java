package com.nnamdi.taskmanager.mock;

import com.nnamdi.taskmanager.dto.Error;
import com.nnamdi.taskmanager.dto.*;
import com.nnamdi.taskmanager.model.Task;
import com.nnamdi.taskmanager.utll.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

public class TestMock {
    public static  final String ID = "8D19B947443D4C1BB2700337527BC251";

    public static TaskDto createTaskDto () {
        return  TaskDto.builder()
                .content("Read a book")
                .build();
    }


    public static Task buildTask() {

        TaskDto requestDto = createTaskDto();
        return Task.builder()
                .id(TestMock.ID)
                .isCompleted(false)
                .content(requestDto.getContent())
                .build();
    }

    public static List<Task> buildTaskList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(buildTask());
        return  taskList;
    }

    public static Response buildErrorResponse () {
        List<Error> errors = new ArrayList<>();
        return new Response(ResponseCodes.NOT_FOUND.code(), ConstantsUtil.NOT_FOUND, null, errors);
    }

    public static Response buildResponse (Object data) {
        return new Response(ResponseCodes.SUCCESS.code(), ConstantsUtil.SUCCESSFUL, data, null);
    }

    public static Task buildUpdatedTask() {
        UpdateTaskDto requestDto = buildUpdateTaskDto();
        return Task.builder()
                .id(TestMock.ID)
                .isCompleted(true)
                .content(requestDto.getContent())
                .build();
    }

    public static UpdateTaskDto buildUpdateTaskDto() {
        return UpdateTaskDto.builder()
                .isCompleted(true)
                .content("Shades of grey")
                .build();
    }


}
