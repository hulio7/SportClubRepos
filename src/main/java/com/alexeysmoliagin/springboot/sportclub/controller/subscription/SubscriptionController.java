package com.alexeysmoliagin.springboot.sportclub.controller.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionCreateModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionUpdateModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.SubscriptionMapper.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.service.SubscriptionService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @GetMapping("/subscription/{id}")
    public SubscriptionResponseModel getSubscription(@NotNull @PathVariable int id) {
        SubscriptionDto subscriptionDto = subscriptionService.getSubscription(id);
        return subscriptionMapper.toModel(subscriptionDto);
    }

    @PostMapping("/subscription")
    public SubscriptionResponseModel createSubscription(@RequestBody SubscriptionCreateModel model) {
        SubscriptionDto subscriptionDto = subscriptionService.createSubscription(subscriptionMapper.toDto(model));
        return subscriptionMapper.toModel(subscriptionDto);
    }

    @PostMapping("/subscription/{id}")
    public SubscriptionResponseModel updateSubscription(@RequestBody SubscriptionUpdateModel model,
                                                        @NotNull @PathVariable int id) {
        SubscriptionDto subscriptionDto = subscriptionService.updateSubscription(subscriptionMapper.toDto(model), id);
        return subscriptionMapper.toModel(subscriptionDto);
    }

    @DeleteMapping("/subscription/{id}")
    public String deleteSubscription(@PathVariable int id) {
        return subscriptionService.deleteSubscription(id);
    }

}
