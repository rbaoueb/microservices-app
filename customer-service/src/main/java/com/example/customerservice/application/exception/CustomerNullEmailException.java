package com.example.customerservice.application.exception;

public class CustomerNullEmailException extends RuntimeException {
    public CustomerNullEmailException(String message) {
        super(message);
    }
}
