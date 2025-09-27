package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.usecase.CreateCustomerOutboxUseCase;
import com.example.customerservice.domain.usecase.UpdateCustomerOutboxUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionalUpdateCustomerService {

    private final UpdateCustomerOutboxUseCase updateCustomerUseCase;

    @Transactional
    public Customer create(Customer customer) {
        // Appel du Use Case pur à l’intérieur d’une transaction
//        return null;
        return updateCustomerUseCase.execute(customer);
    }
}