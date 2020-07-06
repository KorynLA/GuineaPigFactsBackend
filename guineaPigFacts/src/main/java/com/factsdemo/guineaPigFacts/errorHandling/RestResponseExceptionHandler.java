package com.factsdemo.guineaPigFacts.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * Exception class handler that returns corresponding responses to errors thrown in the controller classes
 */
@RestControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleBadId(IdNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleNonUniqueUser(UserFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
