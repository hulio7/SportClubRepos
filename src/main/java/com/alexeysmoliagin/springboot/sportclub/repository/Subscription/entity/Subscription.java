package com.alexeysmoliagin.springboot.sportclub.repository.Subscription.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "subscription")
@Data
public class Subscription {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private TypeSubscription typeSubscription;
}
