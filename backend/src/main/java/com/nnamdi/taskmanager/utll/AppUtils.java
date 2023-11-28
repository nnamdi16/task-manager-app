package com.nnamdi.taskmanager.utll;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppUtils {

    public String generateUUIDString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }


}
