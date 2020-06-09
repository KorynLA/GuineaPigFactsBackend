package com.factsdemo.guineaPigFacts.models;
import org.springframework.data.annotation.Id;
import java.util.List;
@Document
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private Contact contactInfo;
    private List<Fact> facts;

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
    public User(String userName, String password, Contact contactInfo, Fact factGiven) {
        this.userName = userName;
        this.password = password;
        this.contactInfo = contactInfo;
        this.facts = factGiven;
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
     * Getter for Fact list array in object
     * Parameters: None
     * Return: Array of Fact objects
     */
    public List<Fact> getFacts() {
        return facts;
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
     * Setter to add another fact to the Fact array
     * Parameters: Fact object
     * Return: None
     */
    public void setFacts(Fact factGiven) {
        facts.add(factGiven);
    }
}