package com.factsdemo.guineaPigFacts.services.implementations;

import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.repositories.UserRepository;
import com.factsdemo.guineaPigFacts.services.UserService;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Implements the business logic needed for the User part of the API.
 * Calls a UserRepository object which will run the data access calls.
 */
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public void saveUser(User user) {
        BasicPasswordEncryptor passwordEncoder = new BasicPasswordEncryptor();
        user.setPassword(passwordEncoder.encryptPassword(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findByContact_Email(String email) {
        return userRepository.findByContactInfo_Email(email);
    }

    public Optional<User> getCurrentUser(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User updateUser(String id, User newUser) {
        BasicPasswordEncryptor passwordEncoder = new BasicPasswordEncryptor();
        userRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setPassword(passwordEncoder.encryptPassword(newUser.getPassword()));
                    user.getContactInfo().setEmail(newUser.getContactInfo().getEmail());
                    user.getContactInfo().setDailyUpdate(newUser.getContactInfo().isDailyUpdate());
                    return userRepository.save(user);
                });
        return newUser;
    }
 }
