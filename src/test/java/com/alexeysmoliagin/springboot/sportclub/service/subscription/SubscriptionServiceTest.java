package com.alexeysmoliagin.springboot.sportclub.service.subscription;

import com.alexeysmoliagin.springboot.sportclub.controller.subscription.model.SubscriptionModelBuyRequest;
import com.alexeysmoliagin.springboot.sportclub.exceptions.EntityNotFoundException;
import com.alexeysmoliagin.springboot.sportclub.mapper.BillingMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.mapper.subscription.SubscriptionMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersubscription.UserSubscriptionMapperImpl;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.SubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.repository.userSubscription.UserSubscription;
import com.alexeysmoliagin.springboot.sportclub.repository.userSubscription.UserSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.event.BillingService;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoBuyRequest;
import com.alexeysmoliagin.springboot.sportclub.service.subscription.dto.SubscriptionDtoExtensionRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UserSubscriptionRepository usersSubscriptionRepository;
    @Mock
    private UserRepository usersRepository;
    @Spy
    private SubscriptionMapperImpl subscriptionMapper;
    @Spy
    private UserSubscriptionMapperImpl userSubscriptionMapper;
    @Spy
    private BillingMapperImpl billingMapper;
    @Mock
    private BillingService billingService;
    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @BeforeEach
    public void init () {
        MessageSource messageSource = mock(MessageSource.class);
        lenient().when(messageSource.getMessage(any(), any(),any()))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        MessageSourceFactory.setMessageSource(messageSource);
    }

    @Test
    @DisplayName("buySubscription: all ok when subscription exists")
    void buySubscription() {
        var buySubscriptionDto = Instancio.create(SubscriptionDtoBuyRequest.class);
        Subscription subscription = new Subscription();
        subscription.setPrice(buySubscriptionDto.getPrice());
        subscription.setType(buySubscriptionDto.getType());
        subscription.setName(buySubscriptionDto.getName());
        when(usersRepository.findById(buySubscriptionDto.getUserId())).thenReturn(Optional.of(new User()));
        when(subscriptionMapper.toSubscription(buySubscriptionDto)).thenCallRealMethod();
        when(subscriptionRepository.save(any())).thenReturn(subscription);
        when(userSubscriptionMapper.toEntity(any(), anyInt())).thenCallRealMethod();
        when(usersSubscriptionRepository.save(any())).thenReturn(any());
        when(subscriptionMapper.toDtoBuy(subscription)).thenCallRealMethod();
        when(billingMapper.toBillingEventDto(any(), anyInt())).thenCallRealMethod();
        doNothing().when(billingService).sendForCalculateTax(any());
        var actual = subscriptionService.buySubscription(buySubscriptionDto);
        assertEquals(buySubscriptionDto.getName(), actual.getName());
        assertEquals(buySubscriptionDto.getType(), actual.getType());
        assertEquals(buySubscriptionDto.getPrice(), actual.getPrice());
    }

    @Test
    @DisplayName("buySubscription: throw EntityNotFoundException when users not exists")
    void buySubscriptionCase2() {
        var subscriptionDto = Instancio.create(SubscriptionDtoBuyRequest.class);
        when(usersRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->
        subscriptionService.buySubscription(subscriptionDto));
    }

    @Test
    @DisplayName("getSubscription: all ok when subscription exists")
    void getSubscription() {
        Subscription subscription = Instancio.create(Subscription.class);
        when(subscriptionRepository.findById(subscription.getId())).thenReturn(Optional.of(subscription));
        var actual = subscriptionService.getSubscription(subscription.getId());
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
        assertThrows(EntityNotFoundException.class, ()-> subscriptionService.getSubscription(anyInt()));
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
        assertThrows(EntityNotFoundException.class, ()-> subscriptionService.deleteSubscription(anyInt()));
    }

    @Test
    @DisplayName("extensionSubscription: all ok when subscription exists")
    void extensionSubscription() {
        Subscription subscription = Instancio.create(Subscription.class);
        User user = Instancio.create(User.class);
        SubscriptionDtoExtensionRequest dto = Instancio.create(SubscriptionDtoExtensionRequest.class);
        when(subscriptionRepository.findById(dto.getSubscriptionId())).thenReturn(Optional.of(subscription));
        when(usersRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
        when(subscriptionMapper.toSubscription(subscription)).thenCallRealMethod();
        when(subscriptionRepository.save(any())).thenReturn(subscription);
        when(userSubscriptionMapper.toEntity(any(), anyInt())).thenCallRealMethod();
        when(usersSubscriptionRepository.save(any())).thenReturn(new UserSubscription());
        var actual = subscriptionService.extensionSubscription(dto);
        assertEquals(subscription.getType(), actual.getType());
        assertEquals(subscription.getName(), actual.getName());
        assertEquals(subscription.getPrice(), actual.getPrice());
    }

    @Test
    @DisplayName("extensionSubscription: throw EntityNotFoundException when subscription not exists")
    void extensionSubscriptionCase2() {
        SubscriptionDtoExtensionRequest subscriptionExtensionDto = Instancio.create(SubscriptionDtoExtensionRequest.class);
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
















