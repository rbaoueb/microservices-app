package com.example.customerservice.application.port.out;

import com.example.customerservice.domain.model.Customer;

public interface CustomerOutboxOut {
    void saveCustomerCreatedEvent(Customer customer);
    void saveCustomerUpdatedEvent(Customer customer);
}
