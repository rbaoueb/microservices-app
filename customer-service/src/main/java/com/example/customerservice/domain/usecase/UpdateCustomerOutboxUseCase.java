package com.example.customerservice.domain.usecase;

import com.example.customerservice.application.exception.CustomerAlreadyExistException;
import com.example.customerservice.application.exception.CustomerNullEmailException;
import com.example.customerservice.application.port.in.CreateCustomerIn;
import com.example.customerservice.application.port.out.CustomerOutboxOut;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.infrastructure.usecase.UseCase;
import org.apache.commons.lang3.StringUtils;

@UseCase
public record UpdateCustomerOutboxUseCase(CustomerRepositoryOut customerRepositoryOut, CustomerOutboxOut customerOutboxOut) implements CreateCustomerIn {


    @Override
    public Customer execute(Customer customer) {
        // Vérifier si le client existe déjà
        var existingCustomer = customerRepositoryOut.findById(customer.getId());

        if (existingCustomer.isEmpty()) {
            throw new CustomerNullEmailException("Customer with id " + customer.getId() + " not found");
        }

        if (StringUtils.isBlank(customer.getEmail())) {
            throw new IllegalArgumentException("Customer email must not be blank");
        }

        // Sauvegarde du client
        Customer updatedCustomer = customerRepositoryOut.save(customer);

        // Sauvegarde dans l’outbox (événement)
        customerOutboxOut.saveCustomerCreatedEvent(updatedCustomer);

        return updatedCustomer;
    }
    }

