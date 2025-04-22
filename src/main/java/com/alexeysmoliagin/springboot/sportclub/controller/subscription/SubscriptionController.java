package com.alexeysmoliagin.springboot.sportclub.controller.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.BuySubscriptionRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionExtensionRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionResponseModel;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionDto;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @PostMapping("/subscription:buy")
    public SubscriptionResponseModel buySubscription(@RequestBody BuySubscriptionRequestModel model) {
        SubscriptionDto dto = subscriptionService.buySubscription(subscriptionMapper.toDto(model));
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

    @PostMapping("/subscription/{id}/extension")
    public SubscriptionResponseModel extensionSubscription(@PathVariable @NotNull int id,
                                                           @RequestBody SubscriptionExtensionRequestModel model) {
        SubscriptionDto dto = subscriptionService.extensionSubscription(subscriptionMapper.toDto(model, id));
        return subscriptionMapper.toResponseModel(dto);
    }

    @GetMapping("/subscription")
    public List<SubscriptionResponseModel> getListSubscription () {
        List <SubscriptionDto> allSubscription =  subscriptionService.getAllSubscription();
        return subscriptionMapper.toListSubscriptionResponseModel(allSubscription);
    }

}
