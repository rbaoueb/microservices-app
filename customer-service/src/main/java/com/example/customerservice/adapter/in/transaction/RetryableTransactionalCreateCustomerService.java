package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetryableTransactionalCreateCustomerService {

    private final TransactionalCreateCustomerService transactionalService;

    @Retryable(
            retryFor = { DataAccessException.class },
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public Customer create(Customer customer) {
        return transactionalService.create(customer);
    }

    @Recover
    public void recover(DataAccessException e, Customer customer) {
        // optionnel : alerter, journaliser ou déplacer la requête dans une file d'attente
        log.error("Echec définitif pour Customer: {}", customer.getEmail());
    }
}
