package com.example.customerservice.adapter.in.mapper;


import com.example.customerservice.adapter.in.rest.dto.CreateCustomerRequest;
import com.example.customerservice.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestMapper {

    public Customer toDomain(CreateCustomerRequest request) {
        // L'ID sera généré par le UseCase
        return new Customer(null, request.firstName(), request.lastName(), request.email(), request.status(), null);
    }
}