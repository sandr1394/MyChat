package com.belyaev.exception.templates;

import lombok.Data;

import java.util.List;

@Data
public class ExceptionRequestParamTemplate {

    private String message;

    private int status;

    private String exception;

    private List<String> errorFieldList;

    public ExceptionRequestParamTemplate(String message, int status, String exception, List<String> errorFieldList) {
        this.message = message;
        this.status = status;
        this.exception = exception;
        this.errorFieldList = errorFieldList;
    }
}
