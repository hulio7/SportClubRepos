package com.alexeysmoliagin.springboot.sportclub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsersGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UsersIncorrectData> handlerException (NoSuchUserException exception) {
        UsersIncorrectData data = new UsersIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
