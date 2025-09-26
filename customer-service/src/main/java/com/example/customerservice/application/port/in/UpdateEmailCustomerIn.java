package com.example.customerservice.application.port.in;

import com.example.customerservice.adapter.in.rest.dto.EmailModificationRequest;
import com.example.customerservice.domain.model.Customer;

@FunctionalInterface
public interface UpdateEmailCustomerIn {

    Customer execute(Long customerId, EmailModificationRequest request);
}
