package com.alexeysmoliagin.springboot.sportclub.mapper.subcription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionCreateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    Subscription toSubscription (SubscriptionDto dto);
    SubscriptionDto toDto (Subscription subscription);
    SubscriptionDto toDto (SubscriptionCreateRequestModel model);
    SubscriptionResponseModel toResponseModel (SubscriptionDto dto);
    SubscriptionDto toDto (SubscriptionUpdateRequestModel model);

}
