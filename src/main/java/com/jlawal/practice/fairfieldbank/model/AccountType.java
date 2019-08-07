package com.jlawal.practice.fairfieldbank.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="account_types")
public class AccountType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountTypeId;
    @NotBlank(message = "Please provide the Account Type Name")
    private String accountTypeName;

    public AccountType(String accountTypeName) {
         this.accountTypeName = accountTypeName;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeId=" + accountTypeId +
                ", accountTypeName='" + accountTypeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return getAccountTypeId().equals(that.getAccountTypeId()) &&
                getAccountTypeName().equals(that.getAccountTypeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountTypeId(), getAccountTypeName());
    }
}
