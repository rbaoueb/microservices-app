package com.example.customerservice.adapter.in.transaction;

import com.example.customerservice.adapter.in.rest.dto.EmailModificationRequest;
import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetryableTransactionInfoCustomerService {

    private TransactionUpdateEmailCustomerService transactionUpdateEmailCustomerService;

    public Customer updateEmailInfo(Long customerId, EmailModificationRequest request) {
        return transactionUpdateEmailCustomerService.updateEmailCustomer(customerId, request);
    }
}
