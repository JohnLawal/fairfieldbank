package com.jlawal.practice.fairfieldbank.repository;

import com.jlawal.practice.fairfieldbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
