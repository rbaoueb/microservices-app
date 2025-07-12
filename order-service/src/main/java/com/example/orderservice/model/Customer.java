package com.example.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}