package com.nnamdi.taskmanager.exception;

import com.nnamdi.taskmanager.dto.Error;
import com.nnamdi.taskmanager.dto.Response;
import com.nnamdi.taskmanager.dto.ResponseCodes;
import com.nnamdi.taskmanager.utll.ConstantsUtil;
import com.nnamdi.taskmanager.utll.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    private final ResponseUtil responseUtil;

    @Autowired
    public ExceptionHandlers(ResponseUtil responseUtil) {
        this.responseUtil = responseUtil;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Response> handleBadRequest(final MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> validationErrors = result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        String customErrorMessage = "Validation failed. Please check your input.";
        String errorMessage = customErrorMessage + " " + String.join(", ", validationErrors);

        return ResponseEntity.badRequest().body(responseUtil.getErrorResponse(new Error(ResponseCodes.INVALID_REQUEST, ConstantsUtil.BAD_REQUEST, errorMessage)));
    }


}
