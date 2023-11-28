package com.nnamdi.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
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
public class TaskDto implements Serializable {

    @Size(max = 500, message = "Content must not exceed {max} characters")
    @NotBlank(message = "Content must be provided")
    private String content;


}
