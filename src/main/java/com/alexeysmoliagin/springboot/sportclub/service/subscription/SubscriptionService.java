package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDto buySubscription(BuySubscriptionDto dto);
    SubscriptionDto getSubscription (int id);
    String deleteSubscription (int id);
    SubscriptionDto extensionSubscription (SubscriptionExtensionDto dto);
    List<SubscriptionDto> getAllSubscription ();

}
