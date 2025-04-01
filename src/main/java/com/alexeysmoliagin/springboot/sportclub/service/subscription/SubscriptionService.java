package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDtoOnlyId;

public interface SubscriptionService {

    SubscriptionDto createSubscription (SubscriptionDto dto, UsersDtoOnlyId usersDtoOnlyId);
    SubscriptionDto updateSubscription (SubscriptionDto dto, int id);
    SubscriptionDto getSubscription (int id);
    String deleteSubscription (int id);
    SubscriptionDto extensionSubscription (SubscriptionDto dto);

}
