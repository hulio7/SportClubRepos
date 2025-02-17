package com.alexeysmoliagin.springboot.sportclub.repository.Users.entity;

import java.time.LocalDateTime;

public class Users {
    private int id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phone;
    private String telegramLogin;
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
