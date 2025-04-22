package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchEntityException;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersubscription.UserSubscriptionMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UserSubscription;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UsersSubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UsersSubscriptionRepository usersSubscriptionRepository;
    @Mock
    private UsersRepository usersRepository;
    @Spy
    private SubscriptionMapperImpl subscriptionMapper;
    @Spy
    private UserSubscriptionMapperImpl userSubscriptionMapper;
    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Test
    @DisplayName("buySubscription: all ok when subscription exists")
    void buySubscription() {
        BuySubscriptionDto buySubscriptionDto = Instancio.create(BuySubscriptionDto.class);
        Subscription subscription = new Subscription();
        subscription.setPrice(buySubscriptionDto.getPrice());
        subscription.setType(buySubscriptionDto.getType());
        subscription.setName(buySubscriptionDto.getName());
        when(usersRepository.findById(buySubscriptionDto.getUserId())).thenReturn(Optional.of(new Users()));
        when(subscriptionMapper.toSubscription(buySubscriptionDto)).thenCallRealMethod();
        when(subscriptionRepository.save(any())).thenReturn(subscription);
        when(userSubscriptionMapper.toEntity(any(), anyInt())).thenCallRealMethod();
        when(usersSubscriptionRepository.save(any())).thenReturn(any());
        when(subscriptionMapper.toDto(subscription)).thenCallRealMethod();
        SubscriptionDto actual = subscriptionService.buySubscription(buySubscriptionDto);
        assertEquals(buySubscriptionDto.getName(), actual.getName());
        assertEquals(buySubscriptionDto.getType(), actual.getType());
        assertEquals(buySubscriptionDto.getPrice(), actual.getPrice());
    }

    @Test
    @DisplayName("buySubscription: throw EntityNotFoundException when users not exists")
    void buySubscriptionCase2() {
        BuySubscriptionDto subscriptionDto = Instancio.create(BuySubscriptionDto.class);
        when(usersRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> subscriptionService.buySubscription(subscriptionDto));
    }

    @Test
    @DisplayName("getSubscription: all ok when subscription exists")
    void getSubscription() {
        Subscription subscription = Instancio.create(Subscription.class);
        when(subscriptionRepository.findById(subscription.getId())).thenReturn(Optional.of(subscription));
        SubscriptionDto actual = subscriptionService.getSubscription(subscription.getId());
        assertEquals(subscription.getType(), actual.getType());
        assertEquals(subscription.getName(), actual.getName());
        assertEquals(subscription.getPrice(), actual.getPrice());
        assertEquals(subscription.getStartOfAction(), actual.getStartOfAction());
        assertEquals(subscription.getEndOfAction(), actual.getEndOfAction());
        verify(subscriptionRepository).findById(subscription.getId());
    }

    @Test
    @DisplayName("getSubscription: throw NoSuchEntityException when subscription not exists")
    void getSubscriptionCase2() {
        when(subscriptionRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchEntityException.class, ()-> subscriptionService.getSubscription(anyInt()));
    }

    @Test
    @DisplayName("deleteSubscription: all ok when subscription exists")
    void deleteSubscription() {
        Subscription subscription = Instancio.create(Subscription.class);
        when(subscriptionRepository.existsById(subscription.getId())).thenReturn(true);
        subscriptionService.deleteSubscription(subscription.getId());
        verify(subscriptionRepository).existsById(subscription.getId());
    }

    @Test
    @DisplayName("deleteSubscription: throw NoSuchEntityException when subscription not exists")
    void deleteSubscriptionCase2() {
        when(subscriptionRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NoSuchEntityException.class, ()-> subscriptionService.deleteSubscription(anyInt()));
    }

    @Test
    @DisplayName("extensionSubscription: all ok when subscription exists")
    void extensionSubscription() {
        Subscription subscription = Instancio.create(Subscription.class);
        SubscriptionExtensionDto subscriptionExtensionDto = Instancio.create(SubscriptionExtensionDto.class);
        when(subscriptionRepository.findById(subscriptionExtensionDto.getSubscriptionId())).thenReturn(Optional.of(subscription));
        when(subscriptionMapper.toSubscription(subscription)).thenCallRealMethod();
        when(subscriptionRepository.save(any())).thenReturn(subscription);
        when(userSubscriptionMapper.toEntity(any(), anyInt())).thenCallRealMethod();
        when(usersSubscriptionRepository.save(any())).thenReturn(new UserSubscription());
        when(subscriptionMapper.toDto(any(Subscription.class))).thenCallRealMethod();
        SubscriptionDto actual = subscriptionService.extensionSubscription(subscriptionExtensionDto);
        assertEquals(subscription.getType(), actual.getType());
        assertEquals(subscription.getName(), actual.getName());
        assertEquals(subscription.getPrice(), actual.getPrice());
    }

    @Test
    @DisplayName("extensionSubscription: throw EntityNotFoundException when subscription not exists")
    void extensionSubscriptionCase2() {
        SubscriptionExtensionDto subscriptionExtensionDto = Instancio.create(SubscriptionExtensionDto.class);
        when(subscriptionRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->subscriptionService.extensionSubscription(subscriptionExtensionDto));
    }

    @Test
    @DisplayName("getAllSubscription: all ok when call getAllSubscription method")
    void getAllSubscription() {
        Subscription subscription = Instancio.create(Subscription.class);
        when(subscriptionRepository.findAll()).thenReturn(List.of(subscription));
        subscriptionService.getAllSubscription();
        verify(subscriptionRepository).findAll();
    }
}
















