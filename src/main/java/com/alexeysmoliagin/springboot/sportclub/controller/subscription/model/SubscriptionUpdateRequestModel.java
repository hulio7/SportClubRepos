package com.alexeysmoliagin.springboot.sportclub.controller.subscription.model;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.TypeSubscription;
import lombok.Data;

@Data
public class SubscriptionUpdateRequestModel {
    private String name;
    private TypeSubscription type;
    private int price;
}
