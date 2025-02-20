package com.alexeysmoliagin.springboot.sportclub.repository.Users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table (name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
