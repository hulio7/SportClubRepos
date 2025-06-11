package com.alexeysmoliagin.springboot.sportclub.controller.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserModelAddRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String gender;
    @NotBlank
    private int age;
    @NotBlank
    private String phone;
    @NotBlank
    private String telegramLogin;

}
