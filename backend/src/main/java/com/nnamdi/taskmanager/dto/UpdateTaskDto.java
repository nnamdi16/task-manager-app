package com.nnamdi.taskmanager.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto implements Serializable {

    private boolean isCompleted;

    @Size(max = 500)
    @Nullable
    private String content;


}
