package com.factsdemo.guineaPigFacts.services;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Implements the business logic needed for the User part of the API.
 * Calls a UserRepository object which will run the data access calls.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Deletes the User from the database with no return value
     * @param id as a String
     */
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    /**
     * Adds the new user to the database with no return value
     * @param User object
     */
    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    /**
     * Finds the User document that is being searched for by its userName
     * @param userName as a String
     * @return the User object
     */
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Finds the User document based on the embedded contact_info document
     * @param email as a String
     * @return the User object
     */
    public User findByContact_Email(String email) {
        return userRepository.findByContactInfo_Email(email);
    }

    /**
     * Finds the User associated with an id if it exists, will be used for JWT
     * @param id as a String
     * @return an Optional User -- a Null value can be returned if no document with the id was found
     */
    public Optional<User> getCurrentUser(String id) {
        return userRepository.findById(id);
    }

    /**
     * Finds the User associated with an id if it exists
     * @param id as a String
     * @return an Optional User -- a Null value can be returned if no document with the id was found
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Finds all Users in the MongoDB database
     * @return List of the Users
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Updates a User if found by id, else inserts the User document into the database
     * @param id as a String
     * @param newUser as a User object
     * @return the User that was added to the database
     */
    public User updateUser(String id, User newUser) {
        userRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setPassword(newUser.getPassword());
                    user.getContactInfo().setEmail(newUser.getContactInfo().getEmail());
                    user.getContactInfo().setDailyUpdate(newUser.getContactInfo().isDailyUpdate());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
        return newUser;
    }
 }
