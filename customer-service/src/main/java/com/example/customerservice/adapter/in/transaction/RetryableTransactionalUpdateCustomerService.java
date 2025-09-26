package com.example.customerservice.adapter.in.transaction;


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
public class RetryableTransactionalUpdateCustomerService {

    private final TransactionalUpdateCustomerService transactionalUpdateCustomerService;

    @Retryable(value = {DataAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Customer update(Long id, Customer customer) {
        return transactionalUpdateCustomerService.update(id, customer);
    }
}
