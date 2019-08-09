package com.jlawal.practice.fairfieldbank.serviceimplementation;

import com.jlawal.practice.fairfieldbank.model.Customer;
import com.jlawal.practice.fairfieldbank.repository.CustomerRepository;
import com.jlawal.practice.fairfieldbank.service.CustomerService;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcreteCustomerService implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll(Sort.by("customerNumber"));
    }

    @Override
    public Page<Customer> getAllCustomersPaged(int page) {
        return customerRepository.findAll(PageRequest.of(page, AppValues.ENTRIES_PER_PAGE.iVal(), Sort.by(AppValues.CUSTOMER_SORT_BY.val())));
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public boolean hasDefaultRecords() {
        return customerRepository.count() > 0;
    }

    @Override
    public Long getNextAvailableCustomerNumber() {
        return (customerRepository.getLastRegisteredCustomerNumber() + 1);
    }
}
