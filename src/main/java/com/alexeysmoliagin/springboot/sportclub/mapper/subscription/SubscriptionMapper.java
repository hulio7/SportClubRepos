package com.alexeysmoliagin.springboot.sportclub.mapper.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionCreateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    Subscription toSubscription (SubscriptionDto dto);
    SubscriptionDto toDto (Subscription subscription);
    SubscriptionDto toDto (SubscriptionCreateRequestModel model);
    SubscriptionResponseModel toResponseModel (SubscriptionDto dto);
    SubscriptionDto toDto (SubscriptionUpdateRequestModel model);

    @Mapping(target = "id", ignore = true)
    Subscription updateEntity(@MappingTarget Subscription subscription, SubscriptionDto dto);
}
