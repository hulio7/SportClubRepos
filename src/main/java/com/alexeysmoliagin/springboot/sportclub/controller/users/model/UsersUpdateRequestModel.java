package com.alexeysmoliagin.springboot.sportclub.controller.users.model;

import lombok.Data;

@Data
public class UsersUpdateRequestModel {
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
}
