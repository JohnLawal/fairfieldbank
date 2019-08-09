package com.jlawal.practice.fairfieldbank.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.NumberFormat;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column
    private Long accountNumber;

    @Column(nullable = false)
    private Double balance = 0.0;

    @NotNull(message = "Please provide the account type")
    @OneToOne
    @JoinColumn(name="account_type_fk")
    private AccountType accountType;

    @ManyToOne()
    private Customer customer;

    public Account() {

    }

    public Account(Long accountNumber, Double balance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    public Account(Long accountNumber, Double balance, AccountType accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.customer = customer;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBalanceAsMoney(){
        NumberFormat usaFormat = NumberFormat.getCurrencyInstance();
        return usaFormat.format(balance);
    }
}
