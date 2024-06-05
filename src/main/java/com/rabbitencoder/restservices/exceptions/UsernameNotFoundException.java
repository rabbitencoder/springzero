package com.rabbitencoder.restservices.exceptions;

/**
 * @author rahul
 * @date 6/4/2024 1:41 PM
 * -
 */

public class UsernameNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;
    //superclass constructor
    public UsernameNotFoundException(String message){
        super(message);
    }
}

    