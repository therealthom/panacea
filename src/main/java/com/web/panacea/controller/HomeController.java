package com.web.panacea.controller;

import java.util.logging.Level;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController {
            
    private static final Logger logger = Logger.getLogger("HomeController");
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpSession session) {
        //return "redirect:/home/user";
        System.out.println(">>>" + session.getAttribute("login"));
        logger.log(Level.INFO, "!!! {0}", session.getAttribute("login"));
        return "dashboard";
    }
}
