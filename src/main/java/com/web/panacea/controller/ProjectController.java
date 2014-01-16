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
import com.web.panacea.domain.Project;
import com.web.panacea.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectServiceImpl;
    
    @RequestMapping(value = "/listProjects", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
    
    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String create(ModelMap model) {
        Project project = new Project();
        model.addAttribute("project",project);
        return "createProject";
    }
    
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String save(ModelMap model, Project project) {
        projectServiceImpl.saveProject(project);
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
    
    @RequestMapping(value = "/disbleProject", method = RequestMethod.GET)
    public String disable(@RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        project.setActive(false);
        project = projectServiceImpl.updateProject(project);
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
    
    @RequestMapping(value = "/enableProject", method = RequestMethod.GET)
    public String enable(@RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        project.setActive(true);
        project = projectServiceImpl.updateProject(project);
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
}