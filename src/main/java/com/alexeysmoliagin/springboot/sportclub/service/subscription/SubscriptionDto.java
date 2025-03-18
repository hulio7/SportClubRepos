package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.TypeSubscription;
import lombok.Data;

@Data
public class SubscriptionDto {
    private int id;
    private String name;
    private TypeSubscription type;
    private int price;
}
