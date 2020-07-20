package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Model for the Contact object.
 * Class is  embedded in User collection.
 */
public class Contact {
    @Email(message = "Email provided is not in correct format.")
    @NotEmpty
    @Indexed(unique = true)
    private String email;
    @NotNull(message = "Do you want to be updated? Daily update is boolean (true / false).")
    private boolean dailyUpdate;

    /**
     * Empty constructor needed for new instance, the default constructor.
     * No default values are given to an object.
     */
    public Contact() {}

    /**
     * Constructor that will be called to create the new instance with parameters.
     * Sets all values of object to new instance values
     * @param dailyUpdate, email
     */
    public Contact(String email, Boolean dailyUpdate) {
        this.email = email;
        this.dailyUpdate = dailyUpdate;
    }

    /**
     * Setter for dailyUpdate in object
     * @param dailyUpdate
     */
    public void setDailyUpdate(boolean dailyUpdate) {
        this.dailyUpdate = dailyUpdate;
    }

    /**
     * Setter for email in object
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for email in object
     * @return email as a String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for dailyUpdate in object
     * @return dailyUpdate as a boolean
     */
    public boolean isDailyUpdate() {
        return dailyUpdate;
    }
}
