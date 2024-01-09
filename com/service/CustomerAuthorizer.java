package com.service;

import com.entity.Customer;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;
import jakarta.ws.rs.container.ContainerRequestContext;

public class CustomerAuthorizer implements Authorizer<Customer> {


    @Override
    public boolean authorize(Customer customer, String role,
                             @Nullable ContainerRequestContext containerRequestContext)
    {
        return customer.getCustomerName().equals(role);
    }
}