package com.alexeysmoliagin.springboot.sportclub.exception_hundling;

import jakarta.persistence.EntityNotFoundException;

public class NoSuchUserException extends EntityNotFoundException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
