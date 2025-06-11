package com.alexeysmoliagin.springboot.sportclub.service.subscription.dto;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.TypeSubscription;
import lombok.Data;

@Data
public class SubscriptionDtoBuyRequest {
    private String name;
    private TypeSubscription type;
    private int price;
    private Integer userId;
}
