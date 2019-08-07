package com.jlawal.practice.fairfieldbank.serviceimplementation;

import com.jlawal.practice.fairfieldbank.model.Customer;
import com.jlawal.practice.fairfieldbank.repository.CustomerRepository;
import com.jlawal.practice.fairfieldbank.service.CustomerService;
import com.jlawal.practice.fairfieldbank.utility.AppStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcreteCustomerService implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomers(int page) {
        return customerRepository.findAll(PageRequest.of(page, AppStrings.ENTRIES_PER_PAGE.iVal(), Sort.by(AppStrings.CUSTOMER_SORT_BY.val())));
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }
}
