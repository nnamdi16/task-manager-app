package com.nnamdi.taskmanager.controller;

public abstract class BaseApiController {
    private BaseApiController() {
    }

    public static final String BASE_API_PATH = "/api/v1/";
    public static final String TASK_MANAGER = "task-manager";
}
