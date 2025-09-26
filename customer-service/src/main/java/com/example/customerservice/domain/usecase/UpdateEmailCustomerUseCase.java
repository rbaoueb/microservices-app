package com.example.customerservice.domain.usecase;

import com.example.customerservice.adapter.in.rest.dto.EmailModificationRequest;
import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.exception.ResourceNotFoundException;
import com.example.customerservice.application.port.in.UpdateEmailCustomerIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;


@UseCase
public record UpdateEmailCustomerUseCase(
        CustomerRepositoryOut customerRepositoryOut,
        CustomerOutboxOut customerOutboxOut) implements UpdateEmailCustomerIn {

    @Override
    public Customer execute(Long customerId, EmailModificationRequest request) {
        var dbCustomer = customerRepositoryOut.findById(customerId);
        var customer = dbCustomer.orElseThrow(() -> new ResourceNotFoundException("CUSTOMER%s"));
        // Update the Email
        customer.setEmail(request.newEmail());
        var customerUpdated = customerRepositoryOut.save(customer);
        // Push the event into Kafka topic
        customerOutboxOut.saveCustomerCreatedEvent(customer);
        return customerUpdated;
    }
}
