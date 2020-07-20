package com.factsdemo.guineaPigFacts.errorHandling;
/**
 * Exception class called when the User is trying to add a userName or email that is not unique
 */
public class UserFoundException extends RuntimeException{
    /**
     * Provides default message when UserFoundException is thrown
     * @param objectName
     * @param value
     */
    public UserFoundException(String objectName, String value){
        super("Need unique values for "+ objectName+ ". "+value+ " is not unique.");
    }
}