package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchEntityException;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional
    @Override
    public SubscriptionDto createSubscription(SubscriptionDto dto) {
        var subscription = subscriptionRepository.save(subscriptionMapper.toSubscription(dto));
        return subscriptionMapper.toDto(subscription);
    }

    @Transactional
    @Override
    public SubscriptionDto updateSubscription(SubscriptionDto dto, int id) {
        var subscription = subscriptionRepository.findById(id).
                orElseThrow(() -> new NoSuchEntityException(String.format("Абонемент с ID %d не найден", id)));
        var updateSubscription = subscriptionRepository.save(subscriptionMapper.updateEntity(subscription, dto));
            return subscriptionMapper.toDto(updateSubscription);
    }

    @Override
    public SubscriptionDto getSubscription(int id) {
        var subscription = subscriptionRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException(String.format("Абонемент с ID %d не найден", id)));
            return subscriptionMapper.toDto(subscription);
    }

    @Transactional
    @Override
    public String deleteSubscription(int id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return String.format("Абонемент с ID %d удален", id);
        }
        throw new NoSuchEntityException(String.format("Абонемент с ID %d не найден", id));
    }
}
