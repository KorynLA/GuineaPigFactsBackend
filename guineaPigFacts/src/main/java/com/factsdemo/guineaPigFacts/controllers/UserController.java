package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/created")
    public String createUser() {
        return "User Created";
    }
    @GetMapping("/{id)")
    public Optional<User> getUser(@PathVariable("id") String id) {
        return userService.findById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<String>("Added", HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<String>("Added", HttpStatus.OK);
    }

}
