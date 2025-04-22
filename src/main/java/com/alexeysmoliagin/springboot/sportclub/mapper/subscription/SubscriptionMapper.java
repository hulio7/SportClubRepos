package com.alexeysmoliagin.springboot.sportclub.mapper.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.BuySubscriptionRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionExtensionRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.BuySubscriptionDto;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionDto;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionExtensionDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    @Mapping(target = "id", ignore = true)
    Subscription toSubscription (SubscriptionDto dto);
    @Mapping(target = "id", ignore = true)
    Subscription toSubscription (BuySubscriptionDto dto);
    SubscriptionDto toDto (Subscription subscription);
    BuySubscriptionDto toDto (BuySubscriptionRequestModel model);
    SubscriptionResponseModel toResponseModel (SubscriptionDto dto);
    @Mapping(target = "subscriptionId", source = "id")
    SubscriptionExtensionDto toDto (SubscriptionExtensionRequestModel model, @NotNull int id);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startOfAction", ignore = true)
    @Mapping(target = "endOfAction", ignore = true)
    Subscription toSubscription(Subscription subscription);
    List<SubscriptionDto> toListSubscriptionDto (List<Subscription> listSubscription);
    List<SubscriptionResponseModel> toListSubscriptionResponseModel (List<SubscriptionDto> listSubscription);

    @Mapping(target = "id", ignore = true)
    Subscription updateEntity(@MappingTarget Subscription subscription, SubscriptionDto dto);
}
