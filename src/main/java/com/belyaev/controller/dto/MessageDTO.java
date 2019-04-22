package com.belyaev.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class MessageDTO {

    @NotNull(message = "Should be initialized")
    private Long dialogId;

    @NotNull(message = "Should be initialized")
    private String text;

    @NotNull(message = "Should be initialized")
    private Long userId;

    @NotNull(message = "Should be initialized")
    private LocalDateTime timeDateOfMessageCreation;
}
