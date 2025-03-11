package com.alexeysmoliagin.springboot.sportclub.mapper.SubscriptionMapper;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionCreateModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionUpdateModel;
import com.alexeysmoliagin.springboot.sportclub.repository.Subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    SubscriptionDto toDto (Subscription subscription);

    Subscription toSubscription (SubscriptionDto dto);

    SubscriptionResponseModel toModel (SubscriptionDto dto);

    SubscriptionDto toDto (SubscriptionCreateModel model);

    SubscriptionDto toDto (SubscriptionUpdateModel model);

}
