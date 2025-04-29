package com.alexeysmoliagin.springboot.sportclub.repository.event.billingEvent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table (name = "billing_event_info")
@Data
public class BillingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_billing_event")
    private String idBillingEvent;

    @Column(name = "status")
    private String status;

    @Column(name = "date_event")
    private LocalDateTime date_event;

}
