package com.proit.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler {
    /**
     * Handle internal error.
     *
     * @param ex of type {@link RuntimeException}
     * @return the response entity
     */
    @ExceptionHandler({BindException.class})
    public ResponseEntity<Object> handleBindException(BindException ex) {
        log.warn(ex.getMessage());

        Map<String, Object> body = new HashMap<>();

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + " " + x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("status", "error");
        body.put("message", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle internal error.
     *
     * @param ex of type {@link RuntimeException}
     * @return the response entity
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleInternalException(Throwable ex) {

        log.error(ex.getMessage());
        log.error("Exception", ex);

        Map<String, Object> body = new HashMap<>();

        body.put("status", "error");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
