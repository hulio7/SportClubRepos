package com.alexeysmoliagin.springboot.sportclub.controller.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionCreateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionExtensionRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersOnlyIdModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionService;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionDto;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDtoOnlyId;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;
    private final UsersMapper usersMapper;

    @PostMapping("/subscription")
    public SubscriptionResponseModel createSubscription(@RequestBody SubscriptionCreateRequestModel model,
                                                        @RequestBody UsersOnlyIdModel userModel) {
        SubscriptionDto dto = subscriptionService.createSubscription(subscriptionMapper.toDto(model),
                usersMapper.toDtoOnlyId(userModel));
        return subscriptionMapper.toResponseModel(dto);
    }

    @PostMapping("/subscription/{id}")
    public SubscriptionResponseModel updateSubscription(@RequestBody SubscriptionUpdateRequestModel model,
                                                        @PathVariable @NotNull int id) {
        SubscriptionDto dto = subscriptionService.updateSubscription(subscriptionMapper.toDto(model), id);
        return subscriptionMapper.toResponseModel(dto);
    }

    @GetMapping("/subscription/{id}")
    public SubscriptionResponseModel getSubscription (@PathVariable @NotNull int id) {
        SubscriptionDto dto = subscriptionService.getSubscription(id);
        return subscriptionMapper.toResponseModel(dto);
    }

    @DeleteMapping("/subscription/{id}")
    public String deleteSubscription (@PathVariable @NotNull int id) {
        return subscriptionService.deleteSubscription(id);
    }

    @PostMapping("/subscription/extension")
    public SubscriptionResponseModel extensionSubscription(@RequestBody SubscriptionExtensionRequestModel model) {
        SubscriptionDto dto = subscriptionService.extensionSubscription(subscriptionMapper.toDto(model));
        return subscriptionMapper.toResponseModel(dto);
    }
}
