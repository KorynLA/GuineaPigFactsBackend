package com.factsdemo.guineaPigFacts.services;

import com.factsdemo.guineaPigFacts.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Deletes the User from the database with no return value
     * @param id as a String
     */
    public void deleteUser(String id);

    /**
     * Adds the new user to the database with no return value
     * @param user as a User object
     */
    public void saveUser(User user);

    /**
     * Finds the User document that is being searched for by its userName
     * @param userName as a String
     * @return the User object
     */
    public User findByUserName(String userName);

    /**
     * Finds the User document based on the embedded contact_info document
     * @param email as a String
     * @return the User object
     */
    public User findByContact_Email(String email);

    /**
     * Finds the User associated with an id if it exists, will be used for JWT
     * @param id as a String
     * @return an Optional User -- a Null value can be returned if no document with the id was found
     */
    public Optional<User> getCurrentUser(String id);

    /**
     * Finds the User associated with an id if it exists
     * @param id as a String
     * @return an Optional User -- a Null value can be returned if no document with the id was found
     */
    public Optional<User> findById(String id);

    /**
     * Finds all Users in the MongoDB database
     * @return List of the Users
     */
    public List<User> findAll();

    /**
     * Updates a User if found by id, else inserts the User document into the database
     * @param id as a String
     * @param newUser as a User object
     * @return the User that was added to the database
     */
    public User updateUser(String id, User newUser);
}
