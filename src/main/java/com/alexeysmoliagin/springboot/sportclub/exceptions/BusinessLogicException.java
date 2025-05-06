package com.alexeysmoliagin.springboot.sportclub.exceptions;

public class BusinessLogicException extends RuntimeException{
    public BusinessLogicException(String message) {
        super(message);
    }
}
