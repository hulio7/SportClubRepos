package com.alexeysmoliagin.springboot.sportclub.service;

import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;

public interface SubscriptionService {
    SubscriptionDto getSubscription (int id);
    SubscriptionDto createSubscription (SubscriptionDto dto);
    SubscriptionDto updateSubscription (SubscriptionDto dto, int id);
    String deleteSubscription (int id);

}
