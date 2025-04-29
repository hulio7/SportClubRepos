package com.alexeysmoliagin.springboot.sportclub.infrastructure.output;

import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event.BillingEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, BillingEventDto> kafkaTemplate;

    public void sendBillingEvent(BillingEventDto dto, String topicName) {
        log.info("Sending Json Serializer : {}", dto);
        kafkaTemplate.send(topicName, dto);
    }

}
