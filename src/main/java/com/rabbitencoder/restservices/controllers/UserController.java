package com.rabbitencoder.restservices.controllers;

import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.exceptions.UserExistsException;
import com.rabbitencoder.restservices.exceptions.UserNotFoundException;
import com.rabbitencoder.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * @author rahul
 * @date 5/22/2024 11:12 AM
 * -
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    //Create User
    //@RequestBody
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) throws UserExistsException {
        try{
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }catch(UserExistsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    //getUserById
    //Path Variable
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){
        try {
            return userService.getUserById(id);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    //updateUserById
    //Path variable
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        try{
            return userService.updateUserById(id, user);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //deleteUserById
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }


    //getUserByUsername
    @GetMapping("/users/username/{username}")
    public User getUserByUsername(@PathVariable("username") String username ){
        return userService.getUserByUsername(username);
    }

}

    