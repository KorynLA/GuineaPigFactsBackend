package com.factsdemo.guineaPigFacts.errorHandling;

public class EmptyCollectionException extends Exception {
    public EmptyCollectionException(String ex) {
        super("No "+ ex+ " was found");
    }
}
