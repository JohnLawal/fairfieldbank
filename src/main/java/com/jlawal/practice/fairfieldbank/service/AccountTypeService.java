package com.jlawal.practice.fairfieldbank.service;

import com.jlawal.practice.fairfieldbank.model.AccountType;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AccountTypeService {

    Page<AccountType> getAllAccountTypes(int page);

    void createAccountType(AccountType accountType);

    Optional<AccountType> getAccountTypeById(Long accountTypeId);

    Optional<AccountType> getAccountTypeByName(String accountTypeName);

    boolean hasDefaultRecords();

}
