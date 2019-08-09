package com.jlawal.practice.fairfieldbank.service;

import com.jlawal.practice.fairfieldbank.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CustomerService {

    Page<Customer> getAllCustomers(int page);

    void createCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long customerId);

    boolean hasDefaultRecords();

    Long getNextAvailableCustomerNumber();
}
