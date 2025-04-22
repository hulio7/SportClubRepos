package com.alexeysmoliagin.springboot.sportclub.controller.subscription.model;

import lombok.Data;

@Data
public class SubscriptionExtensionRequestModel {
    private int price;
    private Integer userId;
}
