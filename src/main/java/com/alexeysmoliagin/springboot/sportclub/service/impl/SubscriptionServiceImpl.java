package com.alexeysmoliagin.springboot.sportclub.service.impl;

import com.alexeysmoliagin.springboot.sportclub.exception_hundling.NoSuchUserException;
import com.alexeysmoliagin.springboot.sportclub.mapper.SubscriptionMapper.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.Subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.Subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.SubscriptionService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionDto getSubscription(int id) {
        Subscription subscription = null;
        Optional<Subscription> optional = subscriptionRepository.findById(id);
        if (optional.isPresent()) {
            subscription = optional.get();
            return subscriptionMapper.toDto(subscription);
        }
        throw new NoSuchUserException("Абонемент с таким ID не найден");
    }

    @Override
    @Transactional
    public SubscriptionDto createSubscription(SubscriptionDto dto) {
        Subscription subscription = subscriptionRepository.save(subscriptionMapper.toSubscription(dto));
        return subscriptionMapper.toDto(subscription);
    }

    @Override
    @Transactional
    public SubscriptionDto updateSubscription(SubscriptionDto dto, int id) {
        if (subscriptionRepository.existsById(id)) {
            Subscription subscription = subscriptionRepository.save(subscriptionMapper.toSubscription(dto));
            return subscriptionMapper.toDto(subscription);
        }
        throw new NoSuchUserException("Абонемент с таким ID не найден");
    }

    @Override
    @Transactional
    public String deleteSubscription(int id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return "Абонемент с ID = " + id + " удален";
        }
        throw new NoSuchUserException("Абонемент с таким ID не найден");
    }
}
