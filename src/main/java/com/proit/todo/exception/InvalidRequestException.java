package com.proit.todo.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message, Throwable ex) {
        super(message, ex);
    }

    public InvalidRequestException(String message) {
        super(message);
    }

}
