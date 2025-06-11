package com.alexeysmoliagin.springboot.sportclub.controller.user.model;

import lombok.Data;

@Data
public class UserModelUpdateRequest {
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
}
