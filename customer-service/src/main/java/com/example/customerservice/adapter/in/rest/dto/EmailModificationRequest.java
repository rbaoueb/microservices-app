package com.example.customerservice.adapter.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
* @author: KINGUE EBANG
*  */


public record EmailModificationRequest(
        @NotNull
        @NotEmpty
        @Size(min=8, max=15, message="The new Email must be between 8 and 15 Characters long")
        String newEmail
        ) {
}
