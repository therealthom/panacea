package com.web.panacea.controller;

import com.web.panacea.beans.UserBean;
import com.web.panacea.domain.Setup;
import com.web.panacea.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    SetupService setupServiceImpl;
            
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        //return "redirect:/home/user";
        return "dashboard";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(ModelMap model) {
        Setup setup = new Setup();
        setup.setSvnHost("a");
        setup.setSvnPort("b");
        setup.setSvnUsername("c");
        setup.setSvnPassword("d");
        setup.setJenkinsHost("e");
        setup.setJenkinsPort("f");
        setup.setJenkinsUsername("g");
        setup.setJenkinsPassword("h");
        setupServiceImpl.saveSetup(setup);
        
        model.addAttribute("command", new UserBean());
        return "user";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(ModelMap model, UserBean userBean) {
        model.addAttribute("userBean", userBean);
        return "result";
    }
}
