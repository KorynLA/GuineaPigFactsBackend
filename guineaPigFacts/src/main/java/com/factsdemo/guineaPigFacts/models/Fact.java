package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

//Class is mapped to the "fact" MongoDB collection
@Document(collection = "Fact")
public class Fact {
    @Id
    private String id;
    private String factValue;
    private String dateCreated;
    private boolean approved;

    /**
     * Empty constructor needed for new instance, the default constructor.
     * No default values are given to an object.
     */
    public Fact() {
    }

    /**
     * Constructor that will be called to create the new instance with parameters.
     * Parameters: factValue, dateCreated, given with a form
     * Sets all values of object to new instance values. "approved" is defaulted to false. "dateCreated" is defaulted
     * to current day
     * Return: None
     */
    public Fact(String factValue) {
        this.factValue = factValue;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(today);
        dateCreated = strDate;
        approved = false;
    }

    /**
     * Getter for factValue in object
     * Parameters: None
     * Return: String
     */
    public String getFactValue() {
        return factValue;
    }

    /**
     * Setter for approved in object
     * Parameters: Boolean
     * Return: None
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Getter for dateCreated in object
     * Parameters: None
     * Return: Date object
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Getter for approved in object
     * Parameters: None
     * Return: Boolean
     */
    public boolean getApproved() {
        return approved;
    }

}