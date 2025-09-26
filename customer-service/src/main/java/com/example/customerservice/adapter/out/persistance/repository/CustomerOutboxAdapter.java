package com.example.customerservice.adapter.out.persistance.repository;

import com.example.customerservice.adapter.out.persistance.entity.CustomerOutboxEntity;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.util.SerializationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerOutboxAdapter implements CustomerOutboxOut {

    private final CustomerOutboxRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveCustomerCreatedEvent(Customer customer) {
        try {
            CustomerOutboxEntity entity = new CustomerOutboxEntity();
            entity.setId(UUID.randomUUID());
            entity.setAggregateId(customer.getId());
            entity.setType("CustomerCreated");
            entity.setCustomer(customer);
            repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save outbox event", e);
        }
    }
}
