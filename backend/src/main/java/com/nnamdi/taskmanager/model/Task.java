package com.nnamdi.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Task Model
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String id;
    private String content;
    private boolean isCompleted;


}
