package com.factsdemo.guineaPigFacts.models;
//Class is mapped to the "contact" MongoDB collection, will be embedded into user collection.
public class Contact {

    private String email;
    private boolean dailyUpdate;

    /**
     * Empty constructor needed for new instance, the default constructor.
     * No default values are given to an object.
     */
    public Contact() {}

    /**
     * Constructor that will be called to create the new instance with parameters.
     * Parameters: email, dailyUpdate
     * Sets all values of object to new instance values
     * Return: None
     */
    public Contact(String email, Boolean dailyUpdate) {
        this.email = email;
        this.dailyUpdate = dailyUpdate;
    }

    /**
     * Setter for dailyUpdate in object
     * Parameters: Boolean
     * Return: None
     */
    public void setDailyUpdate(boolean dailyUpdate) {
        this.dailyUpdate = dailyUpdate;
    }

    /**
     * Setter for email in object
     * Parameters: String
     * Return: None
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for email in object
     * Parameters: None
     * Return: String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for dailyUpdate in object
     * Parameters: None
     * Return: boolean
     */
    public boolean isDailyUpdate() {
        return dailyUpdate;
    }
}
