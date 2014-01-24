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
import com.web.panacea.domain.Setup;
import com.web.panacea.service.LogService;
import com.web.panacea.service.ProjectService;
import com.web.panacea.service.SetupServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;
import mx.redhat.brms.ws.procesos.impl.ProcessService;
import mx.redhat.brms.ws.procesos.impl.ProcessServiceService;
import mx.redhat.brms.ws.tareas.impl.HumanTaskService;
import mx.redhat.brms.ws.tareas.impl.HumanTaskServiceService;
import mx.redhat.brms.ws.tareas.impl.Parametro;
import mx.redhat.brms.ws.tareas.impl.TaskSummary;
import mx.redhat.brms.ws.tareas.impl.User;
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
    
    @Autowired
    LogService logServiceImpl;
    
    @Autowired
    SetupServiceImpl setupServiceImpl;
    
    @RequestMapping(value = "/listProjects", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
    
    @RequestMapping(value = "/setupProject", method = RequestMethod.GET)
    public String setup(HttpSession session, ModelMap model) {
        ProcessServiceService pss = new ProcessServiceService();
        ProcessService processService = pss.getProcessServicePort();
        long idProceso = processService.iniciaProceso("mx.redhat.ci.CISetupProcess");
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId("admin");
        List<TaskSummary> tareas = service.obtenerTareasGrupos(user, null);
        model.addAttribute("tareas", tareas);
        if("DEV".equalsIgnoreCase(session.getAttribute("role").toString())){
            model.addAttribute("firstPromotion", true);
        } else {
            model.addAttribute("firstPromotion", false);
        }
        return "taskTray";
    }
    
    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String create(@RequestParam Long taskId, ModelMap model) {
        Project project = new Project();
        model.addAttribute("taskId",taskId);
        model.addAttribute("project",project);
        return "createProject";
    }
    
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String save(@RequestParam Long taskId, ModelMap model, Project project) {
        projectServiceImpl.saveProject(project);        
        //Inserta en el log
        Log log = new Log();        
        log.setProject(Project.findProject(project.getId()));
        log.setDateCreated(new Date());
        log.setUsername("admin");
        log.setDescription("Se creó el proyecto");
        logServiceImpl.saveLog(log);        
        List<Parametro> parametros = new ArrayList<Parametro>();
        Setup setup = setupServiceImpl.findSetup(1L); 
        Parametro parametro = new Parametro();
        parametro.setLlave("svnHost_");
        parametro.setValor(setup.getSvnHost());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("svnPort_");
        parametro.setValor(setup.getSvnPort());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("svnUser_");
        parametro.setValor(setup.getSvnUsername());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("svnPassword_");
        parametro.setValor(setup.getSvnPassword());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("svnRepo_");
        parametro.setValor("repo");
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsHost_");
        parametro.setValor(setup.getJenkinsHost());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsPort_");
        parametro.setValor(setup.getJenkinsPort());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsUser_");
        parametro.setValor(setup.getJenkinsUsername());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsPassword_");
        parametro.setValor(setup.getJenkinsPassword());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("operacion_");
        parametro.setValor("crear");
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("proyecto_");
        parametro.setValor(project.getName());
        parametros.add(parametro);
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId("admin");
        Holder<TaskSummary> tarea = new Holder<TaskSummary>();
        tarea.value = new TaskSummary();
        tarea.value.setId(taskId);
        service.iniciarTarea(tarea, user);
        service.completarTarea(tarea, user, parametros);
        model.addAttribute("projects",projectServiceImpl.findAllProjects());
        return "listProjects";
    }
    
    @RequestMapping(value = "/buildProject", method = RequestMethod.GET)
    public String build(HttpSession session, @RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        ProcessServiceService pss = new ProcessServiceService();
        ProcessService processService = pss.getProcessServicePort();
        long idProceso = processService.iniciaProceso("mx.redhat.ci.CIBuildProcess");
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId("admin");
        List<TaskSummary> tareas = service.obtenerTareasGrupos(user, null);
        model.addAttribute("tareas", tareas);
        model.addAttribute("project", project);
        if("DEV".equalsIgnoreCase(session.getAttribute("role").toString())){
            model.addAttribute("firstPromotion", true);
        } else {
            model.addAttribute("firstPromotion", false);
        }
        return "taskTray";
    }
    
    @RequestMapping(value = "/executeBuildProject", method = RequestMethod.GET)
    public String executeBuild(@RequestParam Long projectId, @RequestParam Long taskId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        List<Parametro> parametros = new ArrayList<Parametro>();
        Setup setup = setupServiceImpl.findSetup(1L);
        Parametro parametro = new Parametro();
        parametro.setLlave("jenkinsHost_");
        parametro.setValor(setup.getJenkinsHost());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsPort_");
        parametro.setValor(setup.getJenkinsPort());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsUser_");
        parametro.setValor(setup.getJenkinsUsername());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("jenkinsPassword_");
        parametro.setValor(setup.getJenkinsPassword());
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("operacion_");
        parametro.setValor("ejecutar");
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("proyecto_");
        parametro.setValor(project.getName());
        parametros.add(parametro);
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId("admin");
        Holder<TaskSummary> tarea = new Holder<TaskSummary>();
        tarea.value = new TaskSummary();
        tarea.value.setId(taskId);
        service.iniciarTarea(tarea, user);
        service.completarTarea(tarea, user, parametros);
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