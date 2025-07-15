package com.example.orderservice.port.in;

import com.example.orderservice.dto.OrderDTO;

@FunctionalInterface
public interface RetrieveOrderPort {
    OrderDTO execute(Long id);
}
