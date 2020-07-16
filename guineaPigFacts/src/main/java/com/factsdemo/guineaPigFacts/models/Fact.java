package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

/**
 * Class is mapped to the "fact" MongoDB collection
 */

@Document(collection = "Fact")
public class Fact {
    @Id
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "Id provided is not viable. Needs to be longer.")
    private String id;
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = ".*\\s.*", message = "Fact provided needs at least 8 characters and a space.")
    private String factValue;
    private Date dateCreated = new Date();
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
     * @param factValue
     * Sets all values of object to new instance values. "approved" is defaulted to false. "dateCreated" is defaulted
     * to current day
     */
    public Fact(String factValue, boolean approved) {
        this.factValue = factValue;
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
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Setter for dateCreated in object
     * @param date
     */
    public void setDateCreated(Date date) { dateCreated = date; }

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

    /**
     * Getter for id from object
     * @return id as a String
     */
    public String getId() {
        return id;
    }

}