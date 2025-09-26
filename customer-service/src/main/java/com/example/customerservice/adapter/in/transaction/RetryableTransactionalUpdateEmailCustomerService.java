package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.adapter.in.rest.dto.UpdateEmailCustomerRequest;
import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetryableTransactionalUpdateEmailCustomerService {

    private final TransactionalUpdateEmailCustomerService transactionalUpdateEmailCustomerService;

    @Retryable(
            retryFor = { DataAccessException.class },
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public Customer updateEmailCustomer(Long id, String email) {
        return transactionalUpdateEmailCustomerService.updateEmailCustomer(id, email);
    }
}
