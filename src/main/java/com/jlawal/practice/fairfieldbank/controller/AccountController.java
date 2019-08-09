package com.jlawal.practice.fairfieldbank.controller;

import com.jlawal.practice.fairfieldbank.model.Account;
import com.jlawal.practice.fairfieldbank.model.AccountType;
import com.jlawal.practice.fairfieldbank.model.Customer;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteAccountService;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteAccountTypeService;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteCustomerService;
import com.jlawal.practice.fairfieldbank.utility.AppHelper;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    ConcreteAccountService concreteAccountService;
    @Autowired
    ConcreteAccountTypeService concreteAccountTypeService;
    @Autowired
    ConcreteCustomerService concreteCustomerService;

    @GetMapping(value = {"/fairfieldbank/accounts", "/fairfieldbank/accounts/list"})
    public ModelAndView viewAccounts(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Existing Accounts");
        modelAndView.addObject("pageLinks", AppHelper.pageLinks);
        modelAndView.addObject("accounts", concreteAccountService.getAllAccounts(page));
        modelAndView.addObject("netLiquidity", concreteAccountService.getNetLiquidityAsMoney());
        modelAndView.addObject("currentPageNo", page);
        modelAndView.setViewName(AppValues.VIEW_ACCOUNTS_PAGE.val());
        return modelAndView;
    }

    @GetMapping(value = {"/fairfieldbank/accounts/new"})
    public ModelAndView getNewAccountCreationPage() {
        Account account = new Account();
        account.setCustomer(new Customer());
        account.setAccountType(new AccountType());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(getModelMap());
        modelAndView.addObject("account", account);
        modelAndView.setViewName(AppValues.NEW_ACCOUNT_PAGE.val());
        return modelAndView;
    }

    @PostMapping(value = {"/fairfieldbank/accounts/new"})
    public String createNewAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                //create the account
                System.out.println(account.getCustomer());
                System.out.println(account.getAccountType());
                Long customerNumber = account.getCustomer().getCustomerNumber();
                Customer owningCustomer = concreteCustomerService.getCustomerByCustomerNumber(customerNumber).get();

                Long accountTypeId = account.getAccountType().getAccountTypeId();
                AccountType accountType = concreteAccountTypeService.getAccountTypeById(accountTypeId).get();

                account.setAccountNumber(concreteAccountService.getNextAvailableAccountNumber());
                account.setAccountType(accountType);
                account.setCustomer(owningCustomer);

                owningCustomer.addAccount(account);
                concreteCustomerService.saveCustomer(owningCustomer);

                return AppValues.REDIRECT.val() + AppHelper.pageLinks.get("viewAccounts");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("creationException", "Failed! An Error Occurred While Trying To Create This Account");
                return AppValues.NEW_ACCOUNT_PAGE.val();
            }
        } else {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAllAttributes(getModelMap());

            return AppValues.NEW_ACCOUNT_PAGE.val();
        }
    }

    private Map<String, Object> getModelMap() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("pageTitle", "Create A New Account");
        modelMap.put("pageLinks", AppHelper.pageLinks);
        modelMap.put("accountTypes", concreteAccountTypeService.getAllAccountTypes());
        modelMap.put("customers", concreteCustomerService.getAllCustomers());
        return modelMap;
    }
}
