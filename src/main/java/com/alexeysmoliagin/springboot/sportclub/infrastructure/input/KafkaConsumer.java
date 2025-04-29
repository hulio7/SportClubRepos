package com.alexeysmoliagin.springboot.sportclub.infrastructure.input;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;
import com.alexeysmoliagin.springboot.sportclub.service.event.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final BillingService billingService;


    @KafkaListener(topics = "billingRS", groupId = "group1",
            containerFactory = "billingKafkaListenerContainerFactory")
    void listenerBillingEvent (ConsumerRecord<String, Object> billingEvent) {
        BillingEventDto value = (BillingEventDto) billingEvent.value();
        log.info("Received message through MessageConverterUserListener [{}]", value);
        billingService.updateStatus(value);
    }
}
