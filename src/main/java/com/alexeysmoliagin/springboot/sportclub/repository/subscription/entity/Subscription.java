package com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity;

import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "subscription")
public class Subscription {
    @Column (name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private TypeSubscription type;
    @Column(name = "price")
    private int price;
    @Column(name = "start_of_action")
    private LocalDateTime startOfAction;
    @Column(name = "end_of_action")
    private LocalDateTime endOfAction;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "user_subscription", joinColumns = @JoinColumn (name = "subscription_id"),
            inverseJoinColumns = @JoinColumn (name = "user_id"))
    private List<Users> users;

    public void addUserToSubscription (Users user) {
        if (users==null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
