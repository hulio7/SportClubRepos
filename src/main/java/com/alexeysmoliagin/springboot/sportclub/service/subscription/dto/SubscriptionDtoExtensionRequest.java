package com.alexeysmoliagin.springboot.sportclub.service.subscription.dto;

import lombok.Data;

@Data
public class SubscriptionDtoExtensionRequest {
    private int subscriptionId;
    private int userId;
    private int price;
}
