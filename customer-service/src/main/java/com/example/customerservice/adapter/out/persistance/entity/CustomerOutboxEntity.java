package com.example.customerservice.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_outbox")
public class CustomerOutboxEntity {

    @Id
    private UUID id;

    @Column(name = "aggregate_id")
    private Long aggregateId;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "payload")
    private byte[] payload;

    @Column(name = "sent")
    private boolean sent = false;
}