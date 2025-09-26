package com.example.customerservice.application.port.out;

import com.example.customerservice.domain.model.Customer;

@FunctionalInterface
public interface CustomerOutboxOut {

    void execute(Customer customer, String eventType);

}
