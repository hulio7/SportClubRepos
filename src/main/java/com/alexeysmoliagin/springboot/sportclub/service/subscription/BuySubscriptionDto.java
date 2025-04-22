package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.TypeSubscription;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BuySubscriptionDto {
    private Integer userId;
    private String name;
    private TypeSubscription type;
    private int price;
    private LocalDateTime startOfAction;
    private LocalDateTime endOfAction;
}
