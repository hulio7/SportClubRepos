package com.alexeysmoliagin.springboot.sportclub.service.impl;

import com.alexeysmoliagin.springboot.sportclub.exception_hundling.NoSuchUserException;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersMapper.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.service.SubscriptionService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;
    private SubscriptionMapper subscriptionMapper;

    @Transactional
    @Override
    public SubscriptionDto createSubscription(SubscriptionDto dto) {
        Subscription subscription = subscriptionRepository.save(subscriptionMapper.toSubscription(dto));
        return subscriptionMapper.toDto(subscription);
    }

    @Transactional
    @Override
    public SubscriptionDto updateSubscription(SubscriptionDto dto, int id) {
        if (subscriptionRepository.existsById(id)) {
            Subscription subscription = subscriptionRepository.save(subscriptionMapper.toSubscription(dto));
            return subscriptionMapper.toDto(subscription);
        }
        throw new NoSuchUserException("Абонемент с таким ID не найден");
    }


    @Override
    public SubscriptionDto getSubscription(int id) {
        Subscription subscription = null;
        if (subscriptionRepository.existsById(id)) {
            Optional <Subscription> optional = subscriptionRepository.findById(id);
            return subscriptionMapper.toDto(optional.get());
        }
        throw new NoSuchUserException("Абонемент с таким ID не найден");
    }

    @Transactional
    @Override
    public String deleteSubscription(int id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return "Абонемент с ID = " + id + " удален";
        }
        throw new NoSuchUserException("Абонемент с таким ID не найден");
    }
}
