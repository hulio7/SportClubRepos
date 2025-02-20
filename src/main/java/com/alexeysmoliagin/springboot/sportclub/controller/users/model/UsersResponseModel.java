package com.alexeysmoliagin.springboot.sportclub.controller.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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