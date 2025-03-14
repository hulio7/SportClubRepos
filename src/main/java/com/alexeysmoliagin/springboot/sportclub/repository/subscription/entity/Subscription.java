package com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

}
