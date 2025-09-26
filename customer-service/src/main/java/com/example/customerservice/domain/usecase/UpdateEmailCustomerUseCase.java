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
    public Customer execute(Long id, String email) throws CustomerAlreadyExistException{
        Boolean existingWithEmail = customerRepositoryOut
                .existingCustomerWithEmail(email);
        if (existingWithEmail) {
            throw new CustomerAlreadyExistException("Email " + email + " is already in use by another customer");
        }

        var updateEmailCustomer = customerRepositoryOut.findById(id);

        if (updateEmailCustomer.isPresent()){
            Customer customer = updateEmailCustomer.get();
            customer.setEmail(email);
            Customer updated = customerRepositoryOut.save(customer);
            customerEventPublisherOut.publishUpdateEmailCustomer(updated);
            return updated;
        }else {
            throw new ResourceNotFoundException("Customer with id " + id + " not found");
        }
    }
}
