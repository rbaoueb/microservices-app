package com.example.orderservice.domain.checker;

@FunctionalInterface
public interface CheckOrderNotExistUseCase {

    void check(Long id);
}
