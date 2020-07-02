package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.errorHandling.EmptyCollectionException;
import com.factsdemo.guineaPigFacts.errorHandling.IdNotFoundException;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Rest endpoints
 * GET /user/
 ** Retrieves all users in the User collection
 * GET /user/{id}
 ** Retrieves user with the ID passed in the URL path
 * POST /user/add
 ** Adds a user to the User collection
 * PUT /user/update/{id}
 ** Updates a document in the User collection with the ID passed in the URL path
 * DELETE /user/delete/{id}
 ** Deletes a document with the ID passed in the URL path
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = {"/", ""})
    public List<User> displayUser() throws EmptyCollectionException {
        List<User> users = userService.findAll();
        if(users.isEmpty()) {
            throw new EmptyCollectionException("User");
        }
        return users;
    }

    @GetMapping("/{id}")
    User getCurrentUser(@PathVariable("id") String id) {
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        return user;
    }

    @PostMapping("/")
    ResponseEntity<String> addUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<String>("Added", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String>  deleteUser(@PathVariable String id){
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        userService.deleteUser(id);
        return new ResponseEntity<String>("Removed", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    User replaceUser(@PathVariable("id") String id, @RequestBody User newUser) {
        return userService.updateUser(id, newUser);
    }

}
