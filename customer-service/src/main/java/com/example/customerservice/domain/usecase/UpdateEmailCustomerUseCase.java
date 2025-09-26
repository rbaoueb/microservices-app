package com.example.customerservice.domain.usecase;

import com.example.customerservice.adapter.in.rest.dto.UpdateEmailCustomerRequest;
import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.exception.ResourceNotFoundException;
import com.example.customerservice.application.port.in.UpdateEmailCustomerIn;
import com.example.customerservice.application.port.out.CustomerEventPublisherOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;

import java.util.Optional;

@UseCase
public record UpdateEmailCustomerUseCase(CustomerRepositoryOut customerRepositoryOut,
                                         CustomerEventPublisherOut customerEventPublisherOut) implements UpdateEmailCustomerIn {
    @Override
    public Customer execute(UpdateEmailCustomerRequest updateEmailCustomerRequest) {
        Boolean existingWithEmail = customerRepositoryOut.existingCustomerWithEmail(updateEmailCustomerRequest.email());
        if (existingWithEmail) {
            throw new CustomerAlreadyExistException("Email " + updateEmailCustomerRequest.email() + " is already in use by another customer");
        }

        var updateEmailCustomer = customerRepositoryOut.findById(updateEmailCustomerRequest.id()
        );

        if (updateEmailCustomer.isPresent()){
            Customer customer = updateEmailCustomer.get();
            customer.setEmail(updateEmailCustomerRequest.email());
            Customer updated = customerRepositoryOut.save(customer);
            customerEventPublisherOut.publishUpdateEmailCustomer(updated);
            return updated;
        }else {
            throw new ResourceNotFoundException("Customer with id " + updateEmailCustomerRequest.id() + " not found");
        }
    }
}
