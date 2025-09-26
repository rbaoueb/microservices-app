package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.adapter.in.rest.dto.UpdateEmailCustomerRequest;
import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.usecase.UpdateEmailCustomerUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionalUpdateEmailCustomerService {

    private final UpdateEmailCustomerUseCase updateEmailCustomerUseCase;

    @Transactional
    public Customer updateEmailCustomer(UpdateEmailCustomerRequest updateEmailCustomerRequest) {
        return updateEmailCustomerUseCase.execute(updateEmailCustomerRequest);
    }
}
