package com.alexeysmoliagin.springboot.sportclub.repository.userssubscription;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_subscription")
public class UserSubscription {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "subscription_id")
    private Integer subscriptionId;
}
