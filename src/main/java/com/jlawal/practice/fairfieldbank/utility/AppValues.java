package com.jlawal.practice.fairfieldbank.utility;

public enum AppValues {
    HOME_PAGE("home/index"), VIEW_ACCOUNTS_PAGE("accounts/list"), NEW_ACCOUNT_PAGE("accounts/new"),
    NEW_ACCOUNT_TYPE_PAGE("accounttypes/new"), VIEW_CUSTOMERS_PAGE("customers/list"), NEW_CUSTOMER_PAGE("customers/new"),
    SITE_ROOT("home"), ACCOUNT_SORT_BY("accountNumber"), ACCOUNT_TYPE_SORT_BY("accountTypeId"),
    CUSTOMER_SORT_BY("lastName"), ENTRIES_PER_PAGE(5);

    private String val;
    private int iVal;

    AppValues(String string) {
        this.val = string;
    }

    AppValues(int iVal) {
        this.iVal = iVal;
    }

    public String val() {
        return this.val;
    }

    public int iVal() {
        return this.iVal;
    }
}
