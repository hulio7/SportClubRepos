package com.alexeysmoliagin.springboot.sportclub.exceptions;

import lombok.Data;

@Data
public class ErrorInfo {
    private int code;
    private String info;
}