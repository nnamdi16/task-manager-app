package com.nnamdi.taskmanager.dto;

public enum ResponseCodes {

    SUCCESS(0),

    INVALID_REQUEST(1),
    NOT_FOUND(2);


    private final Integer code;

    public Integer code() {
        return code;
    }

    ResponseCodes(Integer code) {
        this.code = code;
    }

}
