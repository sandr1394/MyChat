package com.belyaev.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message, Long id) {
        super(message, id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
