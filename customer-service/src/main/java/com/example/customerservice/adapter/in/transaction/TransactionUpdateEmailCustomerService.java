package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.adapter.in.rest.dto.EmailModificationRequest;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.usecase.UpdateEmailCustomerUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionUpdateEmailCustomerService {

    private UpdateEmailCustomerUseCase updateEmailCustomerUseCase;

    @Transactional
    public Customer updateEmailCustomer(Long customerId, EmailModificationRequest request) {
        return updateEmailCustomerUseCase.execute(customerId, request);
    }
}
