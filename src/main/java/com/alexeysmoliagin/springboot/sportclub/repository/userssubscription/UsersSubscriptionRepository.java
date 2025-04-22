package com.alexeysmoliagin.springboot.sportclub.repository.userssubscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface UsersSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteBySubscriptionId (Integer subscriptionId);
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteByUserId(Integer userId);
}
