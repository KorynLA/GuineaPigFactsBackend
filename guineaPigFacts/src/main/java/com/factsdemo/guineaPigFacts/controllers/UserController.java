package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.errorHandling.IdNotFoundException;
import com.factsdemo.guineaPigFacts.errorHandling.UserFoundException;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
     * Parses User so the password is not also returned as an HTTP response. Uses all other parts of
     * User to create the object to be sent.
     * @param user
     * @return JSONObject
     */
    private JSONObject getSafeUser(User user) {
        JSONObject safeUser = new JSONObject();
        safeUser.put("id", user.getId());
        safeUser.put("userName", user.getUserName());
        safeUser.put("contactInfo", user.getContactInfo());
        return safeUser;
    }
    /**
     * Retrieves user with the ID passed in the URL path
     * @param id
     * @return JSON object
     */
    @GetMapping("/{id}")
    JSONObject getCurrentUser(@PathVariable("id") String id) {
        User user = userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        JSONObject safeUser = getSafeUser(user);
        return safeUser;
    }

    /**
     * Adds a user to the User collection
     * @param user
     * @return ResponseEntity with a JSON object and the HttpStatus
     */
    @PostMapping("/")
    ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        if(userService.findByContact_Email(user.getContactInfo().getEmail()) != null) {
            throw new UserFoundException("email", user.getContactInfo().getEmail());
        }
        if(userService.findByUserName(user.getUserName()) != null) {
            throw new UserFoundException("username", user.getUserName());
        }
        userService.saveUser(user);
        JSONObject safeUser = getSafeUser(user);
        return new ResponseEntity<JSONObject>(safeUser, HttpStatus.CREATED);
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
     * @param updatedUser a JSON object
     * @return ResponseEntity with the updated User and the HttpStatus
     */
    @PutMapping("/{id}")
    ResponseEntity<?> replaceUser(@PathVariable("id") String id, @Valid @RequestBody User updatedUser) {
        userService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
        User user = userService.updateUser(id, updatedUser);
        user.setId(id);
        JSONObject safeUser = getSafeUser(user);
        return new ResponseEntity<JSONObject>(safeUser, HttpStatus.OK);
    }

}
