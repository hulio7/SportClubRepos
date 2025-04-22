package com.alexeysmoliagin.springboot.sportclub.controller.subscription.model;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.TypeSubscription;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionExtensionRequestModel {
    private int price;
    private Integer userId;
}
