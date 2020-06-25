package com.factsdemo.guineaPigFacts.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
 * Exception class handler that returns corresponding responses to errors thrown in the controller classes
 */
@RestControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler(EmptyCollectionException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleEmptyCollection(EmptyCollectionException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
    }
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleBadId(IdNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
    }
}
