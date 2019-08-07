package com.jlawal.practice.fairfieldbank.controller;

import com.jlawal.practice.fairfieldbank.utility.AppHelper;
import com.jlawal.practice.fairfieldbank.utility.AppValues;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/fairfieldbank", "/fairfieldbank/home"})
    public ModelAndView displayHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Welcome To Fairfield United Banking Corporation");
        modelAndView.setViewName(AppValues.HOME_PAGE.val());
        modelAndView.addObject("siteRoot", AppValues.SITE_ROOT.val());
        modelAndView.addObject("pageLinks", AppHelper.getPageLinks());
        return modelAndView;
    }
}
