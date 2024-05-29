package com.rabbitencoder.restservices.exceptions;

/**
 * @author rahul
 * @date 5/29/2024 8:14 PM
 * -
 */

public class UserExistsException extends Exception {
    private static final long serialVersionUID = 1L;
    public UserExistsException(String message) {
        super(message);
    }
}

    