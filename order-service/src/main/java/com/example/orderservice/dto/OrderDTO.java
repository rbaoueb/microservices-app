package com.example.orderservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    private CustomerDTO customer;

    private LocalDate date;

    private Double totalPrice;

    private List<OrderItemDTO> items = new ArrayList<>();
}