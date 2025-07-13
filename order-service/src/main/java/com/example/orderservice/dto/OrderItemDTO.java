package com.example.orderservice.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long id;

    private String product;

    private Double price;

    private int quantity;

}
