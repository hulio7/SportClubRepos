package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchEntityException;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapper;
import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDtoOnlyId;
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
    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public SubscriptionDto createSubscription(SubscriptionDto dto, UsersDtoOnlyId usersDtoOnlyId) {
        var subscription = subscriptionMapper.toSubscription(dto);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        subscription.setStartOfAction(tomorrow);
        subscription.setEndOfAction(tomorrow.plusYears(1));
        subscriptionRepository.save(subscription);
        Users user = usersMapper.toUsers(usersDtoOnlyId);
        user.addSubscriptionToUser(subscription);
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

    @Override
    public SubscriptionDto extensionSubscription(SubscriptionDto dto) {
        var subscription = subscriptionMapper.toSubscription(dto);
        subscription.setEndOfAction(dto.getStartOfAction().plusYears(1));
        subscriptionRepository.save(subscription);
        return subscriptionMapper.toDto(subscription);
    }
}
