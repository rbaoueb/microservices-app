package com.example.customerservice.application.port.in;

import com.example.customerservice.adapter.in.rest.dto.UpdateEmailCustomerRequest;
import com.example.customerservice.domain.model.Customer;

@FunctionalInterface
public interface UpdateEmailCustomerIn {

    Customer execute(UpdateEmailCustomerRequest updateEmailCustomerRequest);
}
