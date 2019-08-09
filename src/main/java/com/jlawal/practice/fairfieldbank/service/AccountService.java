package com.jlawal.practice.fairfieldbank.service;

import com.jlawal.practice.fairfieldbank.model.Account;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AccountService {

    Page<Account> getAllAccounts(int page);

    void createAccount(Account account);

    Optional<Account> getAccountById(Long accountId);

    boolean hasDefaultRecords();

    Double computeNetLiquidity();

    String getNetLiquidityAsMoney();

    Long getNextAvailableAccountNumber();

}
