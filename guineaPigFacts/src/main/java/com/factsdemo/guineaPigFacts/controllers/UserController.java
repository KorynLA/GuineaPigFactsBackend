package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.errorHandling.IdNotFoundException;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    User getCurrentUser(@PathVariable("id") String id) {
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        return user;
    }

    @PostMapping("/")
    ResponseEntity<String> addUser(@Valid @RequestBody User user) throws Exception{
        User foundEmail = userService.findByContact_Email(user.getContactInfo().getEmail());
        User foundUserName = userService.findByUserName(user.getUserName());
        if(foundEmail != null || foundUserName != null) {
            throw new Exception("Cannot add non unique user!");
        }
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<String>("Added", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String>  deleteUser(@PathVariable String id){
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        userService.deleteUser(id);
        return new ResponseEntity<String>("Removed", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> replaceUser(@PathVariable("id") String id, @Valid @RequestBody User newUser) {
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        return new ResponseEntity<String>("User Updated", HttpStatus.OK);
    }

}
