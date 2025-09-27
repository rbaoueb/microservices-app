package com.example.customerservice.adapter.out.persistance.repository;

import com.example.customerservice.adapter.out.mapper.CustomerPersistenceMapper;
import com.example.customerservice.adapter.out.persistance.entity.CustomerEntity;
import com.example.customerservice.application.port.out.CustomerRepositoryOut;
import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryOut {

    private final CustomerRepository customerRepository;
    private final CustomerPersistenceMapper mapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity saved = customerRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email).map(mapper::toDomain);
    }

}
