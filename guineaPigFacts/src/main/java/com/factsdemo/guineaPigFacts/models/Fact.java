package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

//Class is mapped to the "fact" MongoDB collection
@Document(collection = "Fact")
public class Fact {
    @Id
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    private String id;
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = ".*\\s.*")
    private String factValue;
    @Pattern(regexp = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$")
    private String dateCreated;
    @NotNull
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
    public Fact(String factValue, boolean approved) {
        this.factValue = factValue;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        String strDate = formatter.format(today);
        dateCreated = strDate;
        this.approved = approved;
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
    /**
    * Setter used to update ID for testing
     * @param id String that is unique
     * Return: None
     ***/
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

}