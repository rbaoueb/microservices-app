package com.example.orderservice.port.in;

import com.example.orderservice.dto.OrderDTO;

@FunctionalInterface
public interface CreateCustomerPort {

    OrderDTO execute(OrderDTO order);
}
