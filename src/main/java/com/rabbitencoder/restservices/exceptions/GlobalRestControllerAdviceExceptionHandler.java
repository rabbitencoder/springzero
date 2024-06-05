package com.rabbitencoder.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @author rahul
 * @date 6/5/2024 1:56 PM
 * -
 */

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails usernameNotFound(UsernameNotFoundException ex){
        return new CustomErrorDetails(new Date(), "From the GlobalRestControllerAdvice usernameNotFound",
                ex.getMessage());
    }
}

    