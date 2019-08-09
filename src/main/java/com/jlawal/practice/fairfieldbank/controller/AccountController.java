package com.jlawal.practice.fairfieldbank.controller;

import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteAccountService;
import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteCustomerService;
import com.jlawal.practice.fairfieldbank.utility.AppHelper;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    @Autowired
    ConcreteAccountService concreteAccountService;

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
}