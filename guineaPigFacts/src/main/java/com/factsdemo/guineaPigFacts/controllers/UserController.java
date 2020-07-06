package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.errorHandling.IdNotFoundException;
import com.factsdemo.guineaPigFacts.errorHandling.UserFoundException;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Rest endpoints
 * GET /user/{id}
 ** Retrieves user with the ID passed in the URL path
 * POST /user/add
 ** Adds a user to the User collection
 * PUT /user/{id}
 ** Updates a document in the User collection with the ID passed in the URL path
 * DELETE /user/{id}
 ** Deletes a document with the ID passed in the URL path
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Retrieves user with the ID passed in the URL path
     * @param id
     * @return User object
     */
    @GetMapping("/{id}")
    User getCurrentUser(@PathVariable("id") String id) {
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        return user;
    }

    /**
     * Adds a user to the User collection
     * @param user
     * @return ResponseEntity with a message and the HttpStatus
     */
    @PostMapping("/")
    ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        if(userService.findByContact_Email(user.getContactInfo().getEmail()) != null) {
            throw new UserFoundException("email", user.getContactInfo().getEmail());
        }
        if(userService.findByUserName(user.getUserName()) != null) {
            throw new UserFoundException("username", user.getUserName());
        }
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<String>("Added", HttpStatus.CREATED);
    }

    /**
     * Deletes a document with the ID passed in the URL path
     * @param id
     * @return ResponseEntity with a message and the HttpStatus
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String>  deleteUser(@PathVariable String id){
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        userService.deleteUser(id);
        return new ResponseEntity<String>("Removed", HttpStatus.OK);
    }

    /**
     * Updates a document in the User collection with the ID passed in the URL path
     * @param id
     * @param newUser a User object
     * @return ResponseEntity with a message and the HttpStatus
     */
    @PutMapping("/{id}")
    ResponseEntity<String> replaceUser(@PathVariable("id") String id, @Valid @RequestBody User newUser) {
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        return new ResponseEntity<String>("User Updated", HttpStatus.OK);
    }

}
