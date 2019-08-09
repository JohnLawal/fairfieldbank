package com.jlawal.practice.fairfieldbank.controller;

import com.jlawal.practice.fairfieldbank.utility.AppHelper;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @GetMapping(value = {"/", "/fairfieldbank", "/fairfieldbank/home"})
    public ModelAndView displayHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Welcome To Fairfield United Banking Corporation");
        modelAndView.addObject("pageLinks", AppHelper.pageLinks);
        modelAndView.setViewName(AppValues.HOME_PAGE.val());
        return modelAndView;
    }
}
