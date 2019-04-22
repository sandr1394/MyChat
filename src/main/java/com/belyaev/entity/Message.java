package com.belyaev.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Should be initialized")
    private Long messageId;

    @NotNull(message = "Should be initialized")
    private Long dialogId;

    @NotNull(message = "Should be initialized")
    private String text;

    @NotNull(message = "Should be initialized")
    private Long userId;

    @NotNull(message = "Should be initialized")
    private LocalDateTime timeDateOfMessageCreation;
}
