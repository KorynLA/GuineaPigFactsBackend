package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Class is mapped to the "fact" MongoDB collection
 */

@Document(collection = "Fact")
public class Fact {
    @Id
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    private String id;
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = ".*\\s.*")
    private String factValue;
    @NotNull
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
     * @param factValue, dateCreated, given with a form
     * Sets all values of object to new instance values. "approved" is defaulted to false. "dateCreated" is defaulted
     * to current day
     */
    public Fact(String factValue, boolean approved, String dateCreated) {
        this.factValue = factValue;
        this.dateCreated = dateCreated;
        this.approved = approved;
    }

    /**
     * Getter for factValue in object
     * @return factValue as a String
     */
    public String getFactValue() {
        return factValue;
    }

    /**
     * Setter for approved in object
     * @param approved as a boolean
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Getter for dateCreated in object
     * @return dateCreated as a String
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Getter for approved in object
     * @return Boolean
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