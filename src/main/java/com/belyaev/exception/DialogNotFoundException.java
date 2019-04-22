package com.belyaev.exception;

public class DialogNotFoundException extends NotFoundException {

    public DialogNotFoundException(String message, Long id) {
        super(message, id);
    }
}
