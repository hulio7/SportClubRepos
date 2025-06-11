package com.alexeysmoliagin.springboot.sportclub.service.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class UserDtoAddResponse implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
    private LocalDateTime registerData;
}
