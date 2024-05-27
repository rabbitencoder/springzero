package com.rabbitencoder.restservices.services;

import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author rahul
 * @date 5/22/2024 11:07 AM
 * -
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Create User Method
    public User createUser(User user){
        return userRepository.save(user);
    }

    //getUserById
    public Optional<User> getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    //updateUserById
    public User updateUserById(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    //deleteUserById
    public void deleteUserById(Long id ){
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    //getUserByUsername
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}

    