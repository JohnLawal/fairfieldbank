package com.jlawal.practice.fairfieldbank.controller;

import com.jlawal.practice.fairfieldbank.serviceimplementation.ConcreteCustomerService;
import com.jlawal.practice.fairfieldbank.utility.AppHelper;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
    @Autowired
    ConcreteCustomerService concreteCustomerService;

    @GetMapping(value = {"/", "/fairfieldbank/customers", "/fairfieldbank/customers/list"})
    public ModelAndView viewCustomers(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Registered Customers");
        modelAndView.addObject("pageLinks", AppHelper.getPageLinks());
        modelAndView.addObject("customers", concreteCustomerService.getAllCustomers(page));
        modelAndView.addObject("currentPageNo", page);
        modelAndView.setViewName(AppValues.VIEW_CUSTOMERS_PAGE.val());
        return modelAndView;
    }
}
