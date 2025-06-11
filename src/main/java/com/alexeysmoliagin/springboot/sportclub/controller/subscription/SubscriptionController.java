package com.alexeysmoliagin.springboot.sportclub.controller.subscription;

import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoBuyResponse;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelBuyRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelExtensionRequest;
import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelResponse;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.SubscriptionService;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionRequest;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionResponse;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoGetResponse;
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

    @GetMapping("/subscription")
    public List<SubscriptionModelResponse> getAllSubscriptions() {
        List <SubscriptionDtoGetResponse> allSubscription = subscriptionService.getAllSubscription();
        return subscriptionMapper.toSubscriptionModelListResponse(allSubscription);
    }

    @GetMapping("/subscription/{id}")
    public SubscriptionModelResponse getSubscription (@PathVariable @NotNull int id) {
        SubscriptionDtoGetResponse dto = subscriptionService.getSubscription(id);
        return subscriptionMapper.toResponseModel(dto);
    }

    @PostMapping("/subscription:buy")
    public SubscriptionModelResponse buySubscription(@RequestBody SubscriptionModelBuyRequest model) {
        SubscriptionDtoBuyResponse dto = subscriptionService.buySubscription(subscriptionMapper.toDto(model));
        return subscriptionMapper.toResponseModel(dto);
    }

    @PostMapping("/subscription/{id}")
    public SubscriptionModelResponse extensionSubscription(@PathVariable @NotNull Integer id,
                                                           @RequestBody SubscriptionModelExtensionRequest model) {
        SubscriptionDtoExtensionResponse dto = subscriptionService
                .extensionSubscription(subscriptionMapper.toDto(model, id));
        return subscriptionMapper.toModelExtension(dto);
    }

    @DeleteMapping("/subscription/{id}")
    public String deleteSubscription (@PathVariable @NotNull int id) {
        return subscriptionService.deleteSubscription(id);
    }

}
