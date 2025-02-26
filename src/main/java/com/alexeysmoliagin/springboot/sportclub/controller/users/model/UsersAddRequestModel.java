package com.alexeysmoliagin.springboot.sportclub.controller.users.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class UsersAddRequestModel {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String gender;
    private int age;
    @NotBlank
    private String phone;
    @NotBlank
    private String telegramLogin;

}
