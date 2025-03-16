package com.alexeysmoliagin.springboot.sportclub.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class NoSuchUserException extends EntityNotFoundException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
