package com.alexeysmoliagin.springboot.sportclub.service.subscription;

public interface SubscriptionService {

    SubscriptionDto createSubscription (SubscriptionDto dto);
    SubscriptionDto updateSubscription (SubscriptionDto dto, int id);
    SubscriptionDto getSubscription (int id);
    String deleteSubscription (int id);

}
