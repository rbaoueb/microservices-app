package com.example.customerservice.application.port.out;

import com.example.customerservice.domain.model.Customer;

public interface CustomerEventPublisherOut {

    void publishCustomerCreated(Customer customer);
    void publishCustomerUpdated(Customer customer);
}
