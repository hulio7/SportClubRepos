package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchEntityException;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersubscription.UserSubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UserSubscription;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UsersSubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UsersRepository usersRepository;
    private final UsersSubscriptionRepository usersSubscriptionRepository;
    private final UserSubscriptionMapper userSubscriptionMapper;

    @Transactional
    @Override
    public SubscriptionDto buySubscription(BuySubscriptionDto dto) {
        usersRepository.findById(dto.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("Пользователь с %d не найден"));
        var subscription = subscriptionMapper.toSubscription(dto);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        subscription.setStartOfAction(tomorrow);
        subscription.setEndOfAction(tomorrow.plusYears(1));
        Subscription saved = subscriptionRepository.save(subscription);
        UserSubscription userSubscription = userSubscriptionMapper.toEntity(subscription, dto.getUserId());
        usersSubscriptionRepository.save(userSubscription);
        return subscriptionMapper.toDto(saved);
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
            usersSubscriptionRepository.deleteBySubscriptionId(id);
            subscriptionRepository.deleteById(id);
            return String.format("Абонемент с ID %d удален", id);
        }
        throw new NoSuchEntityException(String.format("Абонемент с ID %d не найден", id));
    }

    @Override
    @Transactional
    public SubscriptionDto extensionSubscription(SubscriptionExtensionDto dto) {
        Subscription current = subscriptionRepository.findById(dto.getSubscriptionId())
                .orElseThrow(() -> new EntityNotFoundException("Абонемент с ID %d не найден"));
        var newSubscription = subscriptionMapper.toSubscription(current);
        newSubscription.setStartOfAction(current.getEndOfAction());
        newSubscription.setEndOfAction(current.getEndOfAction().plusYears(1));
        newSubscription.setPrice(dto.getPrice());
        Subscription saved = subscriptionRepository.save(newSubscription);
        UserSubscription entity = userSubscriptionMapper.toEntity(saved, dto.getUserId());
        usersSubscriptionRepository.save(entity);
        return subscriptionMapper.toDto(saved);
    }

    @Override
    public List<SubscriptionDto> getAllSubscription() {
        var allSubscription = subscriptionRepository.findAll();
        return subscriptionMapper.toListSubscriptionDto(allSubscription);
    }
}
