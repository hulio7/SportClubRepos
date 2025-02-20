package com.alexeysmoliagin.springboot.sportclub.controller.users.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
