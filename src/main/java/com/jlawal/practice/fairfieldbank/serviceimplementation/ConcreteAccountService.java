package com.jlawal.practice.fairfieldbank.serviceimplementation;

import com.jlawal.practice.fairfieldbank.model.Account;
import com.jlawal.practice.fairfieldbank.repository.AccountRepository;
import com.jlawal.practice.fairfieldbank.service.AccountService;
import com.jlawal.practice.fairfieldbank.utility.AppStrings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcreteAccountService implements AccountService {
    private AccountRepository accountRepository;

    public ConcreteAccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<Account> getAllAccounts(int page) {
        return accountRepository.findAll(PageRequest.of(page, AppStrings.ENTRIES_PER_PAGE.iVal(), Sort.by(AppStrings.ACCOUNT_SORT_BY.val())));
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }
}
