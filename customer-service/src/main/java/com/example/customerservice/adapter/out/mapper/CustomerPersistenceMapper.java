package com.example.customerservice.adapter.out.mapper;

import com.example.customerservice.adapter.out.persistance.entity.CustomerEntity;
import com.example.customerservice.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceMapper {

    public CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(),null);
    }

    public Customer toDomain(CustomerEntity entity) {
        return new Customer(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
    }
}
