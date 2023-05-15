package com.gkart.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, String> errorMessages;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map<String, String>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exp) {
        errorMessages = new HashMap<>();
        exp.getBindingResult().getFieldErrors().forEach(error -> errorMessages.put(error.getField(), error.getDefaultMessage()));
        Map<String, Map<String, String>> errorMap = new HashMap<>();
        errorMap.put("errors", errorMessages);
        return errorMap;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {BadCredentialsException.class, UsernameNotFoundException.class})
    public Map<String, String> badCredentialsExceptionHandler(Exception exp) {
        errorMessages = new HashMap<>();
        errorMessages.put("error", exp.getMessage());
        return errorMessages;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> customExceptionHandler(CustomException exp) {
        errorMessages = new HashMap<>();
        errorMessages.put("error", exp.getMessage());
        return new ResponseEntity<>(errorMessages, exp.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> exceptionHandler(Exception exp) {
        errorMessages = new HashMap<>();
        errorMessages.put("error", exp.getMessage());
        return errorMessages;
    }
}
