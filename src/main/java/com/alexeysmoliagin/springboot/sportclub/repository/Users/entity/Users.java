package com.alexeysmoliagin.springboot.sportclub.repository.Users.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "users")
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

    public Users(int id, String name, String surname
            , String gender, int age, String phone
            , String telegramLogin, LocalDateTime registerData) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.telegramLogin = telegramLogin;
        this.registerData = registerData;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelegramLogin() {
        return telegramLogin;
    }

    public void setTelegramLogin(String telegramLogin) {
        this.telegramLogin = telegramLogin;
    }

    public LocalDateTime getRegisterData() {
        return registerData;
    }

    public void setRegisterData(LocalDateTime registerData) {
        this.registerData = registerData;
    }
}
