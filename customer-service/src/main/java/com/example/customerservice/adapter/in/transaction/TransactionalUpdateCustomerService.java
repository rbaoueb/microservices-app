package com.example.customerservice.adapter.in.transaction;


import com.example.customerservice.application.port.in.UpdateCustomerPortIn;
import com.example.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionalUpdateCustomerService {

    private final UpdateCustomerPortIn updateCustomerPortIn;

    @Transactional
    public Customer update(Long id, Customer customer) {
        return updateCustomerPortIn.execute(id,customer);
    }
}
