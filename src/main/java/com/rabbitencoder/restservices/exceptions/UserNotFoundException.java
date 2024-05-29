package com.rabbitencoder.restservices.exceptions;

/**
 * @author rahul
 * @date 5/29/2024 10:49 AM
 * -
 */

public class UserNotFoundException extends Exception{
     private static final long serialVersionUID = 1L;
     public UserNotFoundException(String message) {
         super(message);
     }
}

    