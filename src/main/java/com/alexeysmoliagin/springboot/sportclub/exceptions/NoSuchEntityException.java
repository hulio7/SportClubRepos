package com.alexeysmoliagin.springboot.sportclub.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class NoSuchEntityException extends EntityNotFoundException {

    public NoSuchEntityException(String message) {
        super(message);
    }
}
