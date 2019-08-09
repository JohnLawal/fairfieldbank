package com.jlawal.practice.fairfieldbank;

import com.jlawal.practice.fairfieldbank.model.Account;
import com.jlawal.practice.fairfieldbank.model.AccountType;
import com.jlawal.practice.fairfieldbank.model.Customer;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteAccountService;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteAccountTypeService;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class FairfieldbankApplication implements CommandLineRunner {
    @Autowired
    ConcreteAccountService concreteAccountService;
    @Autowired
    ConcreteAccountTypeService concreteAccountTypeService;
    @Autowired
    ConcreteCustomerService concreteCustomerService;

    public static void main(String[] args) {
        SpringApplication.run(FairfieldbankApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        Inserts the default records into the database if they dont exist
        if (!concreteAccountTypeService.hasDefaultRecords()) {
            AccountType[] defaultAccountTypes = new AccountType[]{
                    new AccountType("Checking"),
                    new AccountType("Loan"),
                    new AccountType("Savings")
            };
            for (AccountType accountType : defaultAccountTypes) {
                concreteAccountTypeService.createAccountType(accountType);
            }
        }

        if (!concreteAccountService.hasDefaultRecords() || !concreteCustomerService.hasDefaultRecords()) {
            Account account1 = new Account(100001L, 100590.96, concreteAccountTypeService.getAccountTypeByName("Savings").orElse(null));
            Account account2 = new Account(100002L, 70000.00, concreteAccountTypeService.getAccountTypeByName("Loan").orElse(null));
            Account account3 = new Account(100003L, 314005.25, concreteAccountTypeService.getAccountTypeByName("Checking").orElse(null));

            Customer customer1 = new Customer(10001L, "Anna", "", "Smith", "asmith@gmail.net", "(641) 451-0001", LocalDate.of(1978, 5, 21));
            Customer customer2 = new Customer(10002L, "Bob", "Earl", "Jones", "bob.e.jones@earthlink.org", "(319) 001-0001", LocalDate.of(1964, 12, 7));

            account1.setCustomer(customer1);
            account2.setCustomer(customer1);
            account3.setCustomer(customer2);

            customer1.addAccount(account1);
            customer1.addAccount(account2);
            customer2.addAccount(account3);

            concreteCustomerService.createCustomer(customer1);
            concreteCustomerService.createCustomer(customer2);
        }

    }

}
