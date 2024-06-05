package com.rabbitencoder.restservices.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author rahul
 * @date 6/1/2024 11:47 PM
 * -
 */

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //MethodArgumentNotValidException

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
                "From MethodArgumnetNotValid Exception in GEH", ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, headers, status);    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
                "From HttpRequestMethodNotSupportedException Exception in GEH", ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, headers, status);    }


    //UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex,WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage() + " in GEH", request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage() + " in GEH", request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);

    }

}

    