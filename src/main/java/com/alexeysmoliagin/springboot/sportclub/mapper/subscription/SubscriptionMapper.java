package com.alexeysmoliagin.springboot.sportclub.mapper.subscription;

import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoBuyRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelBuyRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelExtensionRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelResponse;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoBuyResponse;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionRequest;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionResponse;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoGetResponse;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {

    @Mapping(target = "id", ignore = true)
    Subscription toSubscription (SubscriptionDtoBuyRequest dto);

    SubscriptionDtoBuyResponse toDtoBuy (Subscription subscription);

    SubscriptionDtoGetResponse toDtoGet (Subscription subscription);

    SubscriptionDtoBuyRequest toDto (SubscriptionModelBuyRequest model);

    SubscriptionModelResponse toResponseModel (SubscriptionDtoGetResponse dto);

    SubscriptionModelResponse toResponseModel (SubscriptionDtoBuyResponse dto);

    @Mapping(target = "subscriptionId", source = "id")
    SubscriptionDtoExtensionRequest toDto (SubscriptionModelExtensionRequest model, @NotNull int id);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startOfAction", ignore = true)
    @Mapping(target = "endOfAction", ignore = true)
    Subscription toSubscription(Subscription subscription);

    List<SubscriptionDtoGetResponse> toListSubscriptionDto (List<Subscription> listSubscription);

    List<SubscriptionModelResponse> toSubscriptionModelListResponse (List<SubscriptionDtoGetResponse> listSubscription);

    SubscriptionDtoExtensionResponse toDtoExtension(Subscription saved);

    SubscriptionModelResponse toModelExtension(SubscriptionDtoExtensionResponse dto);
}
