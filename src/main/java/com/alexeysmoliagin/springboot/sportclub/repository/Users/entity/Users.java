package com.alexeysmoliagin.springboot.sportclub.repository.users.entity;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "user_subscription", joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "subscription_id"))
    private List <Subscription> subscriptions;

    public void addSubscriptionToUser (Subscription subscription) {
        if (subscriptions==null) {
            subscriptions = new ArrayList<>();
        }
        subscriptions.add(subscription);
    }
}
