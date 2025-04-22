package com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "subscription")
public class Subscription {
    @Column (name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
}
