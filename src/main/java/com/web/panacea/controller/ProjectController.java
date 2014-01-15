/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.web.panacea.controller;

/**
 *
 * @author oscar
 */
import com.web.panacea.domain.Environment;
import com.web.panacea.domain.Project;
import com.web.panacea.service.SetupService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    SetupService setupServiceImpl;
    
    @RequestMapping(value = "/listProjects", method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<Project> projects = new ArrayList<Project>();
        model.addAttribute("projects",projects);
        return "listProjects";
    }
    
    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String create(ModelMap model) {
        Project project = new Project();
        /**
        Set<Environment> environments = new HashSet<Environment>();
        Environment dev = new Environment();
        dev.setName("Development");
        environments.add(dev);
        Environment uat = new Environment();
        uat.setName("UAT");
        environments.add(uat);
        Environment qa = new Environment();
        qa.setName("QA");
        environments.add(qa);
        Environment production = new Environment();
        production.setName("Production");
        environments.add(production);
        Environment algo = new Environment();
        algo.setName("Puto");
        environments.add(algo);
        project.setEnvironments(environments);
        project.setEnvironments(environments);
        **/
        model.addAttribute("project",project);
        return "createProject";
    }
}