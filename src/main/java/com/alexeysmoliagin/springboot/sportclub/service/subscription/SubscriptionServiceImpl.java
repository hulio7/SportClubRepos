package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;
import com.alexeysmoliagin.springboot.sportclub.mapper.BillingMapper;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersubscription.UserSubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.repository.usersubscription.UserSubscription;
import com.alexeysmoliagin.springboot.sportclub.repository.usersubscription.UserSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.event.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.alexeysmoliagin.springboot.sportclub.exceptions.ExceptionFactory.entityNotFoundException;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.SubscriptionMessage.SUBSCRIPTION_DELETE;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.SubscriptionMessage.SUBSCRIPTION_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.UserMessage.USER_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory.getMessage;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserSubscriptionMapper userSubscriptionMapper;
    private final BillingService billingService;
    private final BillingMapper billingMapper;

    @Transactional
    @Override
    public SubscriptionDto buySubscription(BuySubscriptionDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, dto.getUserId()));
        var subscription = subscriptionMapper.toSubscription(dto);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        subscription.setStartOfAction(tomorrow);
        subscription.setEndOfAction(tomorrow.plusYears(1));
        Subscription saved = subscriptionRepository.save(subscription);
        UserSubscription userSubscription = userSubscriptionMapper.toEntity(subscription, dto.getUserId());
        userSubscriptionRepository.save(userSubscription);
        BillingEventDto billingEventDto = billingMapper.toBillingEventDto(user, subscription.getPrice());
        billingService.sendForCalculateTax(billingEventDto);
        return subscriptionMapper.toDto(saved);
    }

    @Override
    public SubscriptionDto getSubscription(int id) {
        var subscription = subscriptionRepository.findById(id)
                .orElseThrow(()-> entityNotFoundException(SUBSCRIPTION_NOT_EXIST, id));
            return subscriptionMapper.toDto(subscription);
    }

    @Transactional
    @Override
    public String deleteSubscription(int id) {
        if (subscriptionRepository.existsById(id)) {
            userSubscriptionRepository.deleteBySubscriptionId(id);
            subscriptionRepository.deleteById(id);
            return getMessage(SUBSCRIPTION_DELETE, id);
        }
        throw entityNotFoundException(SUBSCRIPTION_NOT_EXIST, id);
    }

    @Override
    @Transactional
    public SubscriptionDto extensionSubscription(SubscriptionExtensionDto dto) {
        Subscription current = subscriptionRepository.findById(dto.getSubscriptionId())
                .orElseThrow(() -> entityNotFoundException(SUBSCRIPTION_NOT_EXIST, dto.getSubscriptionId()));
        var newSubscription = subscriptionMapper.toSubscription(current);
        newSubscription.setStartOfAction(current.getEndOfAction());
        newSubscription.setEndOfAction(current.getEndOfAction().plusYears(1));
        newSubscription.setPrice(dto.getPrice());
        Subscription saved = subscriptionRepository.save(newSubscription);
        UserSubscription entity = userSubscriptionMapper.toEntity(saved, dto.getUserId());
        userSubscriptionRepository.save(entity);
        return subscriptionMapper.toDto(saved);
    }

    @Override
    public List<SubscriptionDto> getAllSubscription() {
        var allSubscription = subscriptionRepository.findAll();
        return subscriptionMapper.toListSubscriptionDto(allSubscription);
    }
}
