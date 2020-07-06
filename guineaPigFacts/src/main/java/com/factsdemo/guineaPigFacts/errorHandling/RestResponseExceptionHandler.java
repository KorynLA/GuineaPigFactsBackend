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
    private ErrorMessages errorMessages= new ErrorMessages();
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
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return new ResponseEntity(errorMessages.getMessage(ex.getBindingResult().getFieldError().getField()), status);
    }
}
