package com.belyaev.exception;

public class NotFoundException extends RuntimeException {

    private Long id;

    public Long getId() {
        return id;
    }

    public NotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public NotFoundException(String message) {
        super(message);
    }
}
