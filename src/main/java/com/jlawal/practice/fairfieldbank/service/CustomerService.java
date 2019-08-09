package com.jlawal.practice.fairfieldbank.service;

import com.jlawal.practice.fairfieldbank.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Page<Customer> getAllCustomersPaged(int page);

    void createCustomer(Customer customer);

    void saveCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long customerId);

    Optional<Customer> getCustomerByCustomerNumber(Long customerNumber);

    boolean hasDefaultRecords();

    Long getNextAvailableCustomerNumber();
}
