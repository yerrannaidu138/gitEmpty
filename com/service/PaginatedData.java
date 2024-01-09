package com.service;

import com.entity.Customer;

import java.util.List;

public class PaginatedData {

    private final List<Customer> listOfCustomers;
    private final PaginationMetadata dataContainsNextAndPrev;

    public PaginatedData(List<Customer> data, PaginationMetadata dataContainsNextAndPrev) {
        this.listOfCustomers = data;
        this.dataContainsNextAndPrev = dataContainsNextAndPrev;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public PaginationMetadata getDataContainsNextAndPrev() {
        return dataContainsNextAndPrev;
    }
}
