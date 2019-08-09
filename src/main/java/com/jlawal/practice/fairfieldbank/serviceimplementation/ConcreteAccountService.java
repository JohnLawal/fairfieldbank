package com.jlawal.practice.fairfieldbank.serviceimplementation;

import com.jlawal.practice.fairfieldbank.model.Account;
import com.jlawal.practice.fairfieldbank.repository.AccountRepository;
import com.jlawal.practice.fairfieldbank.service.AccountService;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Optional;

@Service
public class ConcreteAccountService implements AccountService {
    private AccountRepository accountRepository;

    public ConcreteAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<Account> getAllAccounts(int page) {
        return accountRepository.findAll(PageRequest.of(page, AppValues.ENTRIES_PER_PAGE.iVal(), Sort.by(AppValues.ACCOUNT_SORT_BY.val())));
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public boolean hasDefaultRecords() {
        return accountRepository.count() > 0;
    }

    @Override
    public Double computeNetLiquidity() {
        Double sumOfSavingsAccountsBalances = accountRepository.getSumOfBalancesForAccountTypeWithName(AppValues.SAVINGS_ACCOUNT_TYPE_NAME.val());
        Double sumOfCheckingAccountsBalances = accountRepository.getSumOfBalancesForAccountTypeWithName(AppValues.CHECKING_ACCOUNT_TYPE_NAME.val());
        Double sumOfLoanAccountsBalances = accountRepository.getSumOfBalancesForAccountTypeWithName(AppValues.LOAN_ACCOUNT_TYPE_NAME.val());
        return (sumOfSavingsAccountsBalances + sumOfCheckingAccountsBalances) - sumOfLoanAccountsBalances;
    }

    @Override
    public String getNetLiquidityAsMoney() {
        Double netLiquidity = computeNetLiquidity();
        NumberFormat usaFormat = NumberFormat.getCurrencyInstance();
        return usaFormat.format(netLiquidity);
    }

    @Override
    public Long getNextAvailableAccountNumber() {
        return (accountRepository.getLastCreatedAccountNumber() + 1);
    }


}
