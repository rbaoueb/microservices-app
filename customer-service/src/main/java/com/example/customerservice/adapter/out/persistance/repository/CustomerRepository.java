package com.example.customerservice.adapter.out.persistance.repository;

import com.example.customerservice.adapter.out.persistance.entity.CustomerEntity;
import com.example.customerservice.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);
}