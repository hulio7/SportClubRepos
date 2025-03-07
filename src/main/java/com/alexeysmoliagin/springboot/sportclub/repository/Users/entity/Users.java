package com.alexeysmoliagin.springboot.sportclub.repository.Users.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "surname")
    private String surname;
    @Column (name = "gender")
    private String gender;
    @Column (name = "age")
    private int age;
    @Column (name = "phone")
    private String phone;
    @Column (name = "telegram_login")
    private String telegramLogin;
    @Column (name = "register_date")
    private LocalDateTime registerData;
}
