package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.domain.usecase.CreateCustomerOutboxUseCase;
import com.example.customerservice.domain.model.Customer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionalCreateCustomerService {

    private final CreateCustomerOutboxUseCase createCustomerUseCase;

    @Transactional
    public Customer create(Customer customer) {
        // Appel du Use Case pur à l’intérieur d’une transaction
//        return null;
        return createCustomerUseCase.execute(customer);
    }
}