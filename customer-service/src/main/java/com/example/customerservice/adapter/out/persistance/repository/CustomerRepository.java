package com.example.customerservice.adapter.out.persistance.repository;

import com.example.customerservice.adapter.out.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {}