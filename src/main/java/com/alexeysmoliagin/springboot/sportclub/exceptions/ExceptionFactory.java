package com.alexeysmoliagin.springboot.sportclub.exceptions;

import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionFactory {

    public static EntityNotFoundException entityNotFoundException(String label, Object... args){
        return new EntityNotFoundException(MessageSourceFactory.getMessage(label, args));
    }

    public static BusinessLogicException businessLogicException(String label, Object... args){
        return new BusinessLogicException(MessageSourceFactory.getMessage(label, args));
    }

    public static BadRequestException badRequestException(String label, Object... args) {
        return new BadRequestException(MessageSourceFactory.getMessage(label, args));
    }
}
