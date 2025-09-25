package com.example.customerservice.adapter.out.persistance.repository;

import com.example.customerservice.adapter.out.persistance.entity.CustomerOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerOutboxRepository extends JpaRepository<CustomerOutboxEntity, UUID> {

    List<CustomerOutboxEntity> findBySentFalse();
}