package com.nnamdi.taskmanager.utll;


import com.nnamdi.taskmanager.dto.Error;
import com.nnamdi.taskmanager.dto.Response;
import com.nnamdi.taskmanager.dto.ResponseCodes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseUtil {


    public Response getSuccessResponse(Object data) {

        return new Response(ResponseCodes.SUCCESS.code(), ConstantsUtil.SUCCESSFUL, data, null);
    }

    public Response getErrorResponse(Error err) {
        List<Error> errors = new ArrayList<>();
        errors.add(err);
        return new Response(err.getCode(), err.getMessage(), null, errors);
    }


}
