package com.jlawal.practice.fairfieldbank.utility;

import java.util.HashMap;
import java.util.Map;

public class AppHelper {

    public static Map<String, String> getPageLinks() {
        Map<String, String> pageLinks = new HashMap<>();
        pageLinks.put("siteRoot", AppValues.SITE_ROOT.val());
        pageLinks.put("viewCustomers", AppValues.VIEW_CUSTOMERS_PAGE.val());
        pageLinks.put("registerCustomer", AppValues.NEW_CUSTOMER_PAGE.val());
        pageLinks.put("viewAccounts", AppValues.VIEW_ACCOUNTS_PAGE.val());
        pageLinks.put("createAccount", AppValues.NEW_ACCOUNT_PAGE.val());
        pageLinks.put("createAccountType", AppValues.NEW_ACCOUNT_TYPE_PAGE.val());
        return pageLinks;
    }
}
