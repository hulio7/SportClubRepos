package com.alexeysmoliagin.springboot.sportclub.controller.user.model;

public class UserUpdateRequestModel {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String phone;
    private String telegramLogin;


    public UserUpdateRequestModel() {
    }

    public UserUpdateRequestModel(int id, String name, String surname, int age, String gender, String phone, String telegramLogin, String registerDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.telegramLogin = telegramLogin;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

}
