package com.factsdemo.guineaPigFacts.services;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.repositories.UserRepository;
import org.springframework.stereotype.Service;

/***
 * Implements the business logic needed for the User part of the API.
 * Calls a UserRepository object which will run the data access calls.
 */
@Service
public class UserServiceImplementation {
    private UserRepository userRepository;

    void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
