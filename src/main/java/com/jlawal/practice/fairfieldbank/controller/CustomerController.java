package com.jlawal.practice.fairfieldbank.controller;

import com.jlawal.practice.fairfieldbank.model.Customer;
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

@Controller
public class CustomerController {
    @Autowired
    ConcreteCustomerService concreteCustomerService;

    @GetMapping(value = {"/fairfieldbank/customers", "/fairfieldbank/customers/list"})
    public ModelAndView viewCustomers(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Registered Customers");
        modelAndView.addObject("pageLinks", AppHelper.pageLinks);
        modelAndView.addObject("customers", concreteCustomerService.getAllCustomersPaged(page));
        modelAndView.addObject("currentPageNo", page);
        modelAndView.setViewName(AppValues.VIEW_CUSTOMERS_PAGE.val());
        return modelAndView;
    }

    @GetMapping(value = {"/fairfieldbank/customers/new"})
    public ModelAndView getNewCustomerRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Register A New Customer");
        modelAndView.addObject("pageLinks", AppHelper.pageLinks);
        modelAndView.addObject("customer", new Customer());
        modelAndView.setViewName(AppValues.NEW_CUSTOMER_PAGE.val());
        return modelAndView;
    }

    @PostMapping(value = {"/fairfieldbank/customers/new"})
    public String registerNewCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                customer.setCustomerNumber(concreteCustomerService.getNextAvailableCustomerNumber());
                concreteCustomerService.createCustomer(customer);
                return AppValues.REDIRECT.val() + AppHelper.pageLinks.get("viewCustomers");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("registrationException", "Failed! An Error Occurred While Trying To Register This Customer");
                return AppValues.NEW_CUSTOMER_PAGE.val();
            }
        } else {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return AppValues.NEW_CUSTOMER_PAGE.val();
        }
    }
}
