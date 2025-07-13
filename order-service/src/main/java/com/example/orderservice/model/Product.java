package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "product")
@Data
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
}
