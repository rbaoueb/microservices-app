package com.example.orderservice.domain.checker;

import com.example.orderservice.port.out.OrderOutPort;

@FunctionalInterface
public interface CheckOrderNotExistUseCase<T> {

    void check(OrderOutPort orderOutPort, T id);
}
