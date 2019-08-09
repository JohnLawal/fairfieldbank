package com.jlawal.practice.fairfieldbank.repository;

import com.jlawal.practice.fairfieldbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT sum(balance) FROM Account acc " +
            "WHERE acc.accountType.accountTypeName  = :accountTypeName")
    public Double getSumOfBalancesForAccountTypeWithName(@Param("accountTypeName") String accountTypeName);

    @Query("SELECT max(accountNumber) FROM Account")
    public Long getLastCreatedAccountNumber();
}
