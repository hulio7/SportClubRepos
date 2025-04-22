package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import lombok.Data;

@Data
public class SubscriptionExtensionDto {
    private int subscriptionId;
    private int userId;
    private int price;
}
