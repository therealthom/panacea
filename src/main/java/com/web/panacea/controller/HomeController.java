package com.web.panacea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController {
            
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        //return "redirect:/home/user";
        return "dashboard";
    }
}
