package com.belyaev.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class DialogDTO {

    @NotNull(message = "Should be initialized")
    private Long userId;

    @NotNull(message = "Should be initialized")
    private LocalDateTime timeDateOfCreation;
}
