package com.jlawal.practice.fairfieldbank.serviceimplementation;

import com.jlawal.practice.fairfieldbank.model.AccountType;
import com.jlawal.practice.fairfieldbank.repository.AccountTypeRepository;
import com.jlawal.practice.fairfieldbank.service.AccountTypeService;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcreteAccountTypeService implements AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Page<AccountType> getAllAccountTypes(int page) {
        return accountTypeRepository.findAll(PageRequest.of(page, AppValues.ENTRIES_PER_PAGE.iVal(), Sort.by(AppValues.ACCOUNT_TYPE_SORT_BY.val())));
    }

    @Override
    public void createAccountType(AccountType accountType) {
        accountTypeRepository.save(accountType);
    }

    @Override
    public Optional<AccountType> getAccountTypeById(Long accountTypeId) {
        return accountTypeRepository.findById(accountTypeId);
    }

    @Override
    public Optional<AccountType> getAccountTypeByName(String accountTypeName) {
        return accountTypeRepository.findByAccountTypeNameEquals(accountTypeName);
    }

    @Override
    public boolean hasDefaultRecords() {
        return accountTypeRepository.count() > 0;
    }
}
