package com.alexeysmoliagin.springboot.sportclub.controller.users.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class UsersResponseModel {
    private int id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
    private LocalDateTime registerData;

}