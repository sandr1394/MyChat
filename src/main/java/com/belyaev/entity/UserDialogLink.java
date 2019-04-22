package com.belyaev.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userDialogLink")
@Getter
@Setter
public class UserDialogLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Should be initialized")
    private Long Id;

    @NotNull(message = "Should be initialized")
    private Long userId;

    @NotNull(message = "Should be initialized")
    private Long dialogId;
}
