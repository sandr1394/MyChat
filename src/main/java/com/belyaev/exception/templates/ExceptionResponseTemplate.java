package com.belyaev.exception.templates;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponseTemplate {

    private String message;

    private int status;

    private String exception;

}
