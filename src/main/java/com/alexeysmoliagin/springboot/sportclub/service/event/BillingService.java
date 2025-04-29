package com.alexeysmoliagin.springboot.sportclub.service.event;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;

public interface BillingService {
    void sendForCalculateTax(BillingEventDto billingEventDto);
    void updateStatus (BillingEventDto billingEvent);
}
