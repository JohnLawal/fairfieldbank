package com.jlawal.practice.fairfieldbank.repository;

import com.jlawal.practice.fairfieldbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT max(customerNumber) FROM Customer")
    public Long getLastRegisteredCustomerNumber();

    public Optional<Customer> findCustomerByCustomerNumberEquals(Long customerNumber);
}
