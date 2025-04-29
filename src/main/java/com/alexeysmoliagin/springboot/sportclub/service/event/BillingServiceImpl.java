package com.alexeysmoliagin.springboot.sportclub.service.event;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.KafkaProducer;
import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;
import com.alexeysmoliagin.springboot.sportclub.repository.event.billingEvent.BillingEventRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.event.billingEvent.entity.BillingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final KafkaProducer kafkaProducer;
    private final BillingEventRepository billingEventRepository;

    @Transactional
    @Override
    public void sendForCalculateTax(BillingEventDto dto) {
        kafkaProducer.sendBillingEvent(dto, "billingRQ");
        BillingEvent entity = new BillingEvent();
        entity.setIdBillingEvent(dto.getIdBillingEvent());
        entity.setStatus("in processing");
        entity.setDate_event(LocalDateTime.now());
        billingEventRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateStatus(BillingEventDto dto) {
        BillingEvent entity = billingEventRepository.findByIdBillingEvent(dto.getIdBillingEvent());
        entity.setStatus("finished");
        billingEventRepository.save(entity);
    }
}
