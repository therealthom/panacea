package com.web.panacea.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController {
            
    private static final Logger logger = Logger.getLogger(HomeController.class);
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpSession session) {
        logger.info(">>>" + session.getAttribute("login"));
        return "dashboard";
    }
}
