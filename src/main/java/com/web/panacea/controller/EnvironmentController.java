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
import com.web.panacea.service.EnvironmentService;
import com.web.panacea.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

    @Autowired
    EnvironmentService environmentServiceImpl;
    @Autowired
    ProjectService projectServiceImpl;
    
    @RequestMapping(value = "/listEnvironments", method = RequestMethod.GET)
    public String list(@RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        model.addAttribute("project",project);
        model.addAttribute("environments", environmentServiceImpl.findAllByProject(project));
        return "listEnvironments";
    }
    
    @RequestMapping(value = "/createEnvironment", method = RequestMethod.GET)
    public String create(@RequestParam Long projectId, @RequestParam Long taskId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        model.addAttribute("project",project);
        model.addAttribute("taskId",taskId);
        Environment environment = new Environment();
        model.addAttribute("environment", environment);
        return "createEnvironment";
    }
    
    @RequestMapping(value = "/saveEnvironment", method = RequestMethod.POST)
    public String save(@RequestParam Long projectId, ModelMap model, Environment environment) {
        Project project = projectServiceImpl.findProject(projectId);
        Environment newEnvironment = new Environment();
        newEnvironment.setName(environment.getName());
        newEnvironment.setHost(environment.getHost());
        newEnvironment.setPort(environment.getPort());
        newEnvironment.setUsername(environment.getUsername());
        newEnvironment.setPassword(environment.getPassword());
        newEnvironment.setProject(project);
        environmentServiceImpl.saveEnvironment(newEnvironment);
        model.addAttribute("project",project);
        model.addAttribute("environments", environmentServiceImpl.findAllByProject(project));
        return "listEnvironments";
    }
    
}
