package com.rabbitencoder.restservices.controllers;

import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }


    //getUserById
    //Path Variable
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }


    //updateUserById
    //Path variable
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUserById(id, user);
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

    