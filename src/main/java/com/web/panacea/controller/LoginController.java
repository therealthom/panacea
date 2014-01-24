package com.web.panacea.controller;

import com.web.panacea.domain.Login;
import com.web.panacea.service.LoginService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thom
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginServiceImpl;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String processCredentials(HttpSession session, ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password) {
        String viewName = "login";
        if (!username.equals("") && !password.equals("")) {
            Login login = loginServiceImpl.findLoginByUsernameAndPassword(username, password);
            if (login != null) {
                session.setAttribute("login", login);
                session.setAttribute("username", login.getUsername());
                viewName = "redirect:/home/dashboard";
            } else {
                model.addAttribute("login", new Login());
            }
        } else {
            model.addAttribute("login", new Login());
        }
        return viewName;
    }
    
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(ModelMap model) {
        model.addAttribute("login", new Login());
        return "login";
    }
}
