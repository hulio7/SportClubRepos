package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoBuyRequest;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoBuyResponse;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionRequest;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionResponse;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoGetResponse;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionDtoGetResponse> getAllSubscription ();
    SubscriptionDtoGetResponse getSubscription (int id);
    SubscriptionDtoBuyResponse buySubscription(SubscriptionDtoBuyRequest dto);
    SubscriptionDtoExtensionResponse extensionSubscription (SubscriptionDtoExtensionRequest dto);
    String deleteSubscription (int id);


}
