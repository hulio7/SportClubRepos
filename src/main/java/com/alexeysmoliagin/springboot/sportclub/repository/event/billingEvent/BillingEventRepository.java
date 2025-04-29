package com.alexeysmoliagin.springboot.sportclub.repository.event.billingEvent;

import com.alexeysmoliagin.springboot.sportclub.repository.event.billingEvent.entity.BillingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface BillingEventRepository extends JpaRepository<BillingEvent, Integer> {
    BillingEvent findByIdBillingEvent(String eventId);
}
