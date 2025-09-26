package com.example.customerservice.application.port.out;

import com.example.avro.CustomerEvent;
import com.example.customerservice.domain.model.Customer;

@FunctionalInterface
public interface CustomerEventPublisherOut {

    CustomerEvent execute(Customer customer, String eventType);

}
