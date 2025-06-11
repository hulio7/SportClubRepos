package com.alexeysmoliagin.springboot.sportclub.controller.subscription.model;

import lombok.Data;

@Data
public class SubscriptionModelExtensionRequest {
    private int price;
    private Integer userId;
}
