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
            setup.setSvnHost("-");
            setup.setSvnPort("-");
            setup.setSvnUsername("-");
            setup.setSvnPassword("-");
            setup.setJenkinsHost("-");
            setup.setJenkinsPort("-");
            setup.setJenkinsUsername("-");
            setup.setJenkinsPassword("-");
            setupServiceImpl.saveSetup(setup);            
        }
        System.out.println("setup id1 -> " + setup.getId());
        model.addAttribute("setup", setup);
        return "editSetup";
    }

    @RequestMapping(value = "/editSetup", method = RequestMethod.POST)
    public String editSetup(ModelMap model, Setup setup) {
        Setup setupTmp = Setup.findSetup(setup.getId());
        System.out.println("setup id2 -> " + setupTmp.getId());        
        setupServiceImpl.updateSetup(setup);
        model.addAttribute("setup", setupTmp);
        return "editSetup";
    }
}