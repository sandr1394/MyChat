package com.belyaev.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserDialogLinkDTO {

    @NotNull(message = "Should be initialized")
    private Long userId;

    @NotNull(message = "Should be initialized")
    private Long dialogId;
}
