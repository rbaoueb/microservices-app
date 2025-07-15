package com.example.orderservice.port.out;

import com.example.orderservice.dto.OrderDTO;

public interface OrderOutPort {

    OrderDTO create(OrderDTO order);

    OrderDTO fetchById(Long id);

}
