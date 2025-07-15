package com.example.orderservice.port.in;

import com.example.orderservice.dto.OrderDTO;

@FunctionalInterface
public interface CreateOrderPort {

    OrderDTO execute(OrderDTO order);
}
