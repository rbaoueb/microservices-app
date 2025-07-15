package com.example.orderservice.domain.order;

import com.example.orderservice.domain.checker.CheckOrderNotExistUseCase;
import com.example.orderservice.domain.configuration.UseCase;
import com.example.orderservice.domain.exception.ResourceAlreadyExistsException;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.port.in.CreateOrderPort;
import com.example.orderservice.port.out.OrderOutPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateOrderUseCase implements CreateOrderPort {

    private final OrderOutPort orderOutPort;

    private CheckOrderNotExistUseCase<Long> checker = (orderPort,orderId) -> {
        OrderDTO existingOrder = orderPort.fetchById(orderId);
        if(existingOrder != null) {
            throw new ResourceAlreadyExistsException(String.format("order with id %s already exists !", orderId));
        }
    };

    @Override
    public OrderDTO execute(OrderDTO order) {
        checker.check(orderOutPort, order.getId());
        return orderOutPort.create(order);
    }
}
