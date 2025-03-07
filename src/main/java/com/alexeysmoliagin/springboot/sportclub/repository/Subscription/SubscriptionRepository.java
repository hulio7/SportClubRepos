package com.alexeysmoliagin.springboot.sportclub.repository.Subscription;

import com.alexeysmoliagin.springboot.sportclub.repository.Subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
