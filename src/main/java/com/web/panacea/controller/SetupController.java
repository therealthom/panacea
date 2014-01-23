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
            setup.setSvnHost("0.0.0.0");
            setup.setSvnPort("0");
            setup.setSvnUsername("-");
            setup.setSvnPassword("-");
            
            setup.setJenkinsHost("0.0.0.0");
            setup.setJenkinsPort("0");
            setup.setJenkinsUsername("");
            setup.setJenkinsPassword("");
            
            setup.setArtifactoryHost("0.0.0.0");
            setup.setArtifactoryPort("0");
            setup.setArtifactoryUsername("");
            setup.setArtifactoryPassword("");
            setupServiceImpl.saveSetup(setup);            
        }
        model.addAttribute("setup", setup);
        return "editSetup";
    }

    @RequestMapping(value = "/editSetup", method = RequestMethod.POST)
    public String editSetup(ModelMap model, Setup setup) {
        Setup tmp = Setup.findSetup(setup.getId());
        tmp.setSvnHost(setup.getSvnHost());
        tmp.setSvnPort(setup.getSvnPort());
        tmp.setSvnUsername(setup.getSvnUsername());
        tmp.setSvnPassword(setup.getSvnPassword());
        
        tmp.setJenkinsHost(setup.getJenkinsHost());
        tmp.setJenkinsPort(setup.getJenkinsPort());
        tmp.setJenkinsUsername(setup.getJenkinsUsername());
        tmp.setJenkinsPassword(setup.getJenkinsPassword());
        
        tmp.setArtifactoryHost(setup.getArtifactoryHost());
        tmp.setArtifactoryPort(setup.getArtifactoryPort());
        tmp.setArtifactoryUsername(setup.getArtifactoryUsername());
        tmp.setArtifactoryPassword(setup.getArtifactoryPassword());
        
        Setup result = setupServiceImpl.updateSetup(tmp);
        model.addAttribute("setup", result);
        return "editSetup";
    }
}
