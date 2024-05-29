package com.rabbitencoder.restservices.services;

import com.rabbitencoder.restservices.entities.User;
import com.rabbitencoder.restservices.exceptions.UserExistsException;
import com.rabbitencoder.restservices.exceptions.UserNotFoundException;
import com.rabbitencoder.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public User createUser(User user) throws UserExistsException {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser != null){
            throw new UserExistsException("User already exists");
        }
        return userRepository.save(user);
    }

    //getUserById
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not Found in Repository");
        }
        return user;
    }

    //updateUserById
    public User updateUserById(Long id, User user) throws UserNotFoundException {
        if(!userRepository.findById(id).isPresent()){
            throw new UserNotFoundException("User Not Found in Repository with : " + id);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    //deleteUserById
    public void deleteUserById(Long id ) {
        if (!userRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found in Repository");
        }
        userRepository.deleteById(id);
    }

    //getUserByUsername
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}

    