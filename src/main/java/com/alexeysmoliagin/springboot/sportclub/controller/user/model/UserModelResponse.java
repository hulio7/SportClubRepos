package com.alexeysmoliagin.springboot.sportclub.controller.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserModelResponse {
    private int id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
    private LocalDateTime registerData;

}