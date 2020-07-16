package com.factsdemo.guineaPigFacts.errorHandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception class handler that returns corresponding responses to errors thrown in the controller classes
 */
@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method called when IdNotFoundException is thrown
     * @param ex
     * @return ResponseEntity<Object> where object is the default message and HttpStatus code
     */
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleBadId(IdNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Method called when UserFoundException is thrown
     * @param ex
     * @return ResponseEntity<Object> where object is the default message and HttpStatus code
     */
    @ExceptionHandler(UserFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleNonUniqueUser(UserFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
