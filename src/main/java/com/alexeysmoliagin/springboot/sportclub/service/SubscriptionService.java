package com.alexeysmoliagin.springboot.sportclub.service;

import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;

public interface SubscriptionService {

    SubscriptionDto createSubscription (SubscriptionDto dto);
    SubscriptionDto updateSubscription (SubscriptionDto dto, int id);
    SubscriptionDto getSubscription (int id);
    String deleteSubscription (int id);

}
