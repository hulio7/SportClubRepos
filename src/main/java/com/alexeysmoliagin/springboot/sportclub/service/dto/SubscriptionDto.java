package com.alexeysmoliagin.springboot.sportclub.service.dto;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.TypeSubscription;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class SubscriptionDto {
    private int id;
    private String name;
    private TypeSubscription type;
}
