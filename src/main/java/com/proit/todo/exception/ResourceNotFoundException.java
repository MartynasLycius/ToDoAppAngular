package com.proit.todo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
