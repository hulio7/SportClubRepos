package com.alexeysmoliagin.springboot.sportclub.service.user.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDtoUpdateRequest implements Serializable {
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
}
