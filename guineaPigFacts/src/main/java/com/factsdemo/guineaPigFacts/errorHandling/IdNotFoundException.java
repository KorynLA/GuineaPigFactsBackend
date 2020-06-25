package com.factsdemo.guineaPigFacts.errorHandling;
/*
 * Exception class called when there isn't a document of specified ID
 */
public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String idValue, String objectName){
        super(objectName +" "+ idValue +  " was not found");

    }
}
