package com.factsdemo.guineaPigFacts.errorHandling;
/*
 * Exception class called when there is an empty collection being called with a GET request
 */
public class EmptyCollectionException extends Exception {
    public EmptyCollectionException(String objectName) {
        super("No "+ objectName + " was found");
    }
}
