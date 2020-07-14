package com.factsdemo.guineaPigFacts.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Model for the User document.
 * Class has Contact object embedded.
 */
@Document(collection = "User")
public class User {
    @Id
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    private String id;
    @NotEmpty
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    @Indexed(unique = true)
    private String userName;
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = ".*\\d.*")
    private String password;
    @Valid
    private Contact contactInfo;

    /**
     * Empty constructor needed for new instance, the default constructor.
     * No default values are given to an object.
     */
    public User() {}

    /**
     * Constructor that will be called to create the new instance with parameters.
     * @param userName, password, contactInfo, factGiven
     * Sets all values of object to new instance values
     */
    public User(String userName, String password, Contact contactInfo) {
        this.userName = userName;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    /**
     * Getter for userName in object
     * @return String userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for password in object
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for Contact object
     * @return Contact
     */
    public Contact getContactInfo() {
        return contactInfo;
    }

    /**
     * Setter for password in object
     * @param  password as a String. Will be 8 characters long minimum.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for userName in object
     * @param userName
     */
    public void setUserName(String userName) {this.userName = userName; }

    /**
     * Setter for Id in object
     * @param id as String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for Id in object
     * @return id as String
     */
    public String getId() { return id; }
}