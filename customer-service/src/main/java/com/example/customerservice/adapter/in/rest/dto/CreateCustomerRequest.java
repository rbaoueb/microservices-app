package com.example.customerservice.adapter.in.rest.dto;

import jakarta.validation.constraints.NotNull;

public record CreateCustomerRequest(@NotNull String firstName, String lastName, String email) {}
