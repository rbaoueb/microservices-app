package com.example.customerservice.adapter.out.persistance.entity;

import com.example.customerservice.domain.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "payload")
    private Customer customer;

    @Column(name = "sent")
    private boolean sent = false;
}