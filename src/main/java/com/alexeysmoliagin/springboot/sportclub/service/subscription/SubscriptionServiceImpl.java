package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchUserException;
import com.alexeysmoliagin.springboot.sportclub.mapper.subcription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

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
