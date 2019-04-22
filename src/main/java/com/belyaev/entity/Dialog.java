package com.belyaev.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "dialog")
@Getter
@Setter
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Should be initialized")
    private Long id;

    @NotNull(message = "Should be initialized")
    private LocalDateTime timeDateOfCreation;

}
