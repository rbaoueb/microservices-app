package com.example.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rue;
    private String ville;
    private String codePostal;
    private String pays;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
