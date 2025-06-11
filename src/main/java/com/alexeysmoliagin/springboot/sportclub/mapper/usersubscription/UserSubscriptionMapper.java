package com.alexeysmoliagin.springboot.sportclub.mapper.usersubscription;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.repository.userSubscription.UserSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserSubscriptionMapper {
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "subscriptionId", source = "subscription.id")
    @Mapping(target = "id", ignore = true)
    UserSubscription toEntity(Subscription subscription, int userId);
}
