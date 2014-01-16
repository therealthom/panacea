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
import com.web.panacea.domain.Log;
import com.web.panacea.domain.Project;
import com.web.panacea.service.LogService;
import com.web.panacea.service.ProjectService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectServiceImpl;
    
    @Autowired
    LogService logServiceImpl;
    
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
        //Inserta en el log
        Log log = new Log();        
        log.setProject(Project.findProject(project.getId()));
        log.setDateCreated(new Date());
        log.setUsername("admin");
        log.setDescription("Se creó el proyecto");
        logServiceImpl.saveLog(log);        
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
}