package com.factsdemo.guineaPigFacts.errorHandling;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String idValue, String objectName){
        super(objectName +" "+ idValue +  " was not found");

    }
}
