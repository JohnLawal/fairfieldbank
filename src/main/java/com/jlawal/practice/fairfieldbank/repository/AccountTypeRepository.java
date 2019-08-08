package com.jlawal.practice.fairfieldbank.repository;

import com.jlawal.practice.fairfieldbank.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    Optional<AccountType> findByAccountTypeNameEquals(String accountTypeName);
}
