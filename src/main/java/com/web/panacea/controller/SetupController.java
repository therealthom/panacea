package com.web.panacea.controller;

import com.web.panacea.domain.Setup;
import com.web.panacea.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author thom
 */
@Controller
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    SetupService setupServiceImpl;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String setup(ModelMap model) {
        Setup setup = setupServiceImpl.findSetup(1L);
        if (setup == null) {
            setup = new Setup();
        }
        model.addAttribute("command", setup);
        return "editSetup";
    }

    @RequestMapping(value = "/editSetup", method = RequestMethod.POST)
    public String editSetup(ModelMap model, Setup setup) {
        setup.setSvnHost("a");
        setup.setSvnPort("b");
        setup.setSvnUsername("c");
        setup.setSvnPassword("d");
        setup.setJenkinsHost("e");
        setup.setJenkinsPort("f");
        setup.setJenkinsUsername("g");
        setup.setJenkinsPassword("h");
        setupServiceImpl.saveSetup(setup);
        model.addAttribute("setup", setup);
        return "editSetup";
    }
}
