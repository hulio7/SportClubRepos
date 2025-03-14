package com.alexeysmoliagin.springboot.sportclub.repository.subscription;

import com.alexeysmoliagin.springboot.sportclub.repository.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
