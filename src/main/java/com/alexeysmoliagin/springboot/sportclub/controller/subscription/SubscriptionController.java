package com.alexeysmoliagin.springboot.sportclub.controller.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionCreateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersMapper.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.service.SubscriptionService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {

    private SubscriptionService subscriptionService;
    private SubscriptionMapper subscriptionMapper;

    @PostMapping("/subscription")
    public SubscriptionResponseModel updateSubscription(@RequestBody SubscriptionCreateRequestModel model) {
        SubscriptionDto dto = subscriptionService.createSubscription(subscriptionMapper.toDto(model));
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

   // ("/subscription/buy")
//    public String buySubscription () {
//
//    }
}
