package com.belyaev.exception.handler;

import com.belyaev.exception.NotFoundException;
import com.belyaev.exception.PasswordNotMatchException;
import com.belyaev.exception.templates.ExceptionRequestParamTemplate;
import com.belyaev.exception.templates.ExceptionResponseTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionResponseTemplate> handleUserNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponseTemplate(ex.getMessage(), 404, "NotFoundException"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    protected ResponseEntity<ExceptionResponseTemplate> handlePasswordNotMatchException(PasswordNotMatchException ex) {
        return new ResponseEntity<>(new ExceptionResponseTemplate(ex.getMessage(), 401, "PasswordNotMatchException"),
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<String> listOfErrors = new ArrayList<>();
        for (ObjectError error : allErrors) {
            listOfErrors.add(((FieldError) error).getField());
        }
        String message = "Validation failed for requested params";
        return new ResponseEntity<>(new ExceptionRequestParamTemplate(message, 400, "ArgumentNotValidException", listOfErrors),
                HttpStatus.BAD_REQUEST);
    }
}
