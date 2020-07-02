package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model for the User document.
 * Class has Contact object embedded.
 */
@Document(collection = "User")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userName;
    private String password;
    private Contact contactInfo;

    /**
     * Empty constructor needed for new instance, the default constructor.
     * No default values are given to an object.
     */
    public User() {}

    /**
     * Constructor that will be called to create the new instance with parameters.
     * Parameters: userName, password, contactInfo, factGiven
     * Sets all values of object to new instance values
     * Return: None
     */
    public User(String userName, String password, Contact contactInfo) {
        this.userName = userName;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    /**
     * Getter for userName in object
     * Parameters: None
     * Return: String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for password in object
     * Parameters: None
     * Return: String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for Contact object
     * Parameters: None
     * Return: Contact
     */
    public Contact getContactInfo() {
        return contactInfo;
    }

    /**
     * Setter for password in object
     * Parameters: String password. Will be 8 characters long minimum.
     * Return: None
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for userName in object
     * Parameters: String userName
     * Return: None
     */
    public void setUserName(String userName) {this.userName = userName; }

    /**
     * Setter for Id in object
     * @param id as String
     */
    public void setId(String id) {
        this.id = id;
    }
}