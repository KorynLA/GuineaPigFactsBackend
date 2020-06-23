package com.factsdemo.guineaPigFacts.services;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/***
 * Implements the business logic needed for the User part of the API.
 * Calls a UserRepository object which will run the data access calls.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findByContact_Email(String email) {
        return userRepository.findByContactInfo_Email(email);
    }
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
 }
