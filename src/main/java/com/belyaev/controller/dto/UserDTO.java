package com.belyaev.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserDTO {

    @NotNull(message = "Should be initialized")
    private String name;

    @NotNull(message = "Should be initialized")
    private String surname;

    @NotNull(message = "Should be initialized")
    private String nik;

    @NotNull(message = "Should be initialized")
    private String password;
}
