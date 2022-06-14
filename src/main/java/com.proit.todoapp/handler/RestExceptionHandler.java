package com.proit.todoapp.handler;

import com.proit.todoapp.mapper.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import java.util.*;

/**
 * Created by rana on 9/06/22.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends RuntimeException  {

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ex.printStackTrace();
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            ex.printStackTrace();
            return buildResponseEntity(new Response("Database error"+ex.getCause(), null));
        }
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        ex.printStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        builder.append("\n"+ex.getMessage());
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleEntityNotFound(NullPointerException ex) {
        ex.printStackTrace();
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }

    @ExceptionHandler(ServletException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ServletException ex) {
        ex.printStackTrace();
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex) {
        ex.printStackTrace();
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    protected ResponseEntity<Object> unauthorized(RuntimeException ex) {
        ex.printStackTrace();
        return buildResponseEntity(new Response(ex.getMessage(), null));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<Object>(new Response("Failed", errors), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> buildResponseEntity(Response apiError) {
        return new ResponseEntity<Object>(apiError, HttpStatus.OK);
    }
}
