package com.web.panacea.controller;

import com.web.panacea.service.SessionService;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController {
            
    private static final Logger logger = Logger.getLogger(HomeController.class);
    
    @Autowired
    SessionService sessionServiceImpl;
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpSession session, ModelMap model) {
        //logger.info(">>>" + session.getAttribute("login"));
        model.addAttribute("role", sessionServiceImpl.getRole(session));
        return "dashboard";
    }
}
