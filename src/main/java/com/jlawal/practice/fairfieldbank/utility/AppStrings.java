package com.jlawal.practice.fairfieldbank.utility;

public enum AppStrings {
    HOME_PAGE("home/index"), VIEW_ACCOUNTS_PAGE("accounts/list"), NEW_ACCOUNT_PAGE("accounts/new"),
    NEW_ACCOUNT_TYPE_PAGE("account-types/new"), VIEW_CUSTOMERS_PAGE("customers/list"),
    SITE_ROOT("fairfieldbank/"), ACCOUNT_SORT_BY("accountNumber"), ACCOUNT_TYPE_SORT_BY("accountTypeId"),
    CUSTOMER_SORT_BY("lastName"), ENTRIES_PER_PAGE(5);

    private String val;
    private int iVal;

    AppStrings(String string) {
        this.val = string;
    }

    AppStrings(int iVal) {
        this.iVal = iVal;
    }

    public String val() {
        return this.val;
    }

    public int iVal() {
        return this.iVal;
    }
}
