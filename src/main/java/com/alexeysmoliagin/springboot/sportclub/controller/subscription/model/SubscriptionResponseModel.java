package com.alexeysmoliagin.springboot.sportclub.controller.subscription.model;

import com.alexeysmoliagin.springboot.sportclub.repository.Subscription.entity.TypeSubscription;
import lombok.Data;

@Data
public class SubscriptionResponseModel {
    private int id;
    private String name;
    private TypeSubscription typeSubscription;
}
