package com.belyaev.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Should be initialized")
    private Long id;

    @NotNull(message = "Should be initialized")
    private String name;

    @NotNull(message = "Should be initialized")
    private String surname;

    @NotNull(message = "Should be initialized")
    private String nik;

    @NotNull(message = "Should be initialized")
    private String password;
}
