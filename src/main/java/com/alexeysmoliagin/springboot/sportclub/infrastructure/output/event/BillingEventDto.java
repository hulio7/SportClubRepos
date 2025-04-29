package com.alexeysmoliagin.springboot.sportclub.infrastructure.output.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class BillingEventDto {
    private String idBillingEvent;
    private String name;
    private String surname;
    private int age;
    private int price;

}
