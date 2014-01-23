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
import com.web.panacea.domain.PromotionRequest;
import com.web.panacea.domain.Setup;
import com.web.panacea.service.ProjectService;
import com.web.panacea.service.PromotionRequestService;
import com.web.panacea.service.SetupServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/promotion")
public class PromotionRequestController {

    @Autowired
    ProjectService projectServiceImpl;
    @Autowired
    PromotionRequestService promotionRequestServiceImpl;
    @Autowired
    SetupServiceImpl setupServiceImpl;
    
    @RequestMapping(value = "/startPromotionProcess", method = RequestMethod.GET)
    public String startPromotionProcess(@RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        ProcessServiceService pss = new ProcessServiceService();
        ProcessService processService = pss.getProcessServicePort();
        long idProceso = processService.iniciaProceso("mx.redhat.ci.CIPromocionProcess");
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId("admin");
        List<TaskSummary> tareas = service.obtenerTareasGrupos(user, null);
        model.addAttribute("tareas", tareas);
        model.addAttribute("project", project);
        return "taskTray";
    }
    
    @RequestMapping(value = "/generateFirst", method = RequestMethod.GET)
    public String generateFirst(@RequestParam Long taskId, @RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        PromotionRequest newPromotionRequest = new PromotionRequest();
        newPromotionRequest.setComments("Promovido desde desarrollo");
        newPromotionRequest.setDateCreated(new Date());
        newPromotionRequest.setProject(project);
        promotionRequestServiceImpl.savePromotionRequest(newPromotionRequest);
        model.addAttribute("taskId",taskId);
        model.addAttribute("promotion", newPromotionRequest);
        return "showPromotion"; 
    }
    
    @RequestMapping(value = "/promoteToNextLevel", method = RequestMethod.POST)
    public String nextLEvel(@RequestParam String comments, @RequestParam String outcome, @RequestParam Long projectId, @RequestParam Long taskId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        List<Parametro> parametros = new ArrayList<Parametro>();
        Setup setup = setupServiceImpl.findSetup(1L);
        Parametro parametro = new Parametro();
        parametro.setLlave("proyecto_");
        parametro.setValor(project.getName());
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
        parametro.setLlave("outcome_");
        parametro.setValor(outcome);
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("version_");
        parametro.setValor("1.1");
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("comentarios_");
        parametro.setValor(comments);
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
        List<TaskSummary> tareas = service.obtenerTareasGrupos(user, null);
        model.addAttribute("tareas", tareas);
        return "taskTray";  
    }
    
    @RequestMapping(value = "/evaluatePromotion", method = RequestMethod.GET)
    public String evaluate(@RequestParam Long processId, @RequestParam Long taskId, ModelMap model) {
        ProcessServiceService pss = new ProcessServiceService();
        ProcessService processService = pss.getProcessServicePort();
        String nombreProyecto = (String) processService.obtenVariableNodo(processId, "_proyecto");
        Project project = projectServiceImpl.findProjectByName(nombreProyecto);
        PromotionRequest promotionRequest = promotionRequestServiceImpl.findByProject(project);
        model.addAttribute("promotion", promotionRequest);
        model.addAttribute("taskId",taskId);
        return "showPromotion";  
    }
    
    
    
    @RequestMapping(value = "/createFirstPromotionRequest", method = RequestMethod.GET)
    public String createFirst(@RequestParam Long taskId, @RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        model.addAttribute("project",project);
        model.addAttribute("taskId",taskId);
        PromotionRequest promotionRequest = new PromotionRequest();
        model.addAttribute("promotionRequest", promotionRequest);
        return "createFirstPromotionRequest";
    }
    
    @RequestMapping(value = "/saveFirstPromotionRequest", method = RequestMethod.POST)
    public String saveFirst(@RequestParam Long taskId, @RequestParam Long projectId, ModelMap model, PromotionRequest promotionRequest) {
        Project project = projectServiceImpl.findProject(projectId);
        PromotionRequest newPromotionRequest = new PromotionRequest();
        newPromotionRequest.setComments(promotionRequest.getComments());
        newPromotionRequest.setDateCreated(new Date());
        newPromotionRequest.setProject(project);
        promotionRequestServiceImpl.savePromotionRequest(newPromotionRequest);
        List<Parametro> parametros = new ArrayList<Parametro>();
        Setup setup = setupServiceImpl.findSetup(1L);
        Parametro parametro = new Parametro();
        parametro.setLlave("proyecto_");
        parametro.setValor(project.getName());
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
        parametro.setLlave("outcome_");
        parametro.setValor(null);
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("version_");
        parametro.setValor("1.1");
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("comentarios_");
        parametro.setValor(newPromotionRequest.getComments());
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
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<PromotionRequest> promotions = PromotionRequest.findAllPromotionRequests();        
        model.addAttribute("promotions", promotions);
        return "listPromotion";
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam Long taskId, @RequestParam Long promotionId, ModelMap model) {
        PromotionRequest promotion = PromotionRequest.findPromotionRequest(promotionId);
        model.addAttribute("promotion", promotion);
        model.addAttribute("taskId", taskId);
        return "showPromotion";   
    }
}