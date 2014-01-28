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
import com.web.panacea.domain.PromotionRequest;
import com.web.panacea.domain.Setup;
import com.web.panacea.service.ProjectService;
import com.web.panacea.service.PromotionRequestService;
import com.web.panacea.service.SessionService;
import com.web.panacea.service.SetupServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Holder;
import mx.redhat.artifactory.ArtifactoryClient;
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
    @Autowired
    SessionService sessionServiceImpl;
    
    @RequestMapping(value = "/startPromotionProcess", method = RequestMethod.GET)
    public String startPromotionProcess(HttpSession session,@RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        ProcessServiceService pss = new ProcessServiceService();
        ProcessService processService = pss.getProcessServicePort();
        long idProceso = processService.iniciaProceso("mx.org.ife.CIPromocionProcess");
        processService.asignarVariableNodo(idProceso, "_proyecto", project.getName());
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId(session.getAttribute("role").toString());
        List<TaskSummary> tareas = service.obtenerTareasGrupos(user, null);
        model.addAttribute("tareas", tareas);
        model.addAttribute("project", project);
        model.addAttribute("firstPromotion", true);
        return "taskTray";
    }
    
    @RequestMapping(value = "/generateFirst", method = RequestMethod.GET)
    public String generateFirst(@RequestParam Long taskId, @RequestParam Long processId, ModelMap model) {
        ProcessServiceService pss = new ProcessServiceService();
        ProcessService processService = pss.getProcessServicePort();
        String nombreProyecto = (String) processService.obtenVariableNodo(processId, "_proyecto");
        Project project = projectServiceImpl.findProjectByName(nombreProyecto);
        PromotionRequest newPromotionRequest = new PromotionRequest();
        newPromotionRequest.setComments("Promovido desde desarrollo");
        newPromotionRequest.setDateCreated(new Date());
        newPromotionRequest.setProject(project);
        promotionRequestServiceImpl.savePromotionRequest(newPromotionRequest);
        Setup setup = setupServiceImpl.findSetup(1L);
        ArtifactoryClient artifactoryClient = new ArtifactoryClient(setup.getArtifactoryHost(),Integer.parseInt(setup.getArtifactoryPort()), setup.getArtifactoryUsername(), setup.getArtifactoryPassword());
        List<String> versions = artifactoryClient.getVersions(project.getGroupId().replaceAll("\\.","/"), project.getName());
        model.addAttribute("versions",versions);
        model.addAttribute("taskId",taskId);
        model.addAttribute("promotion", newPromotionRequest);
        return "showPromotion"; 
    }
    
    @RequestMapping(value = "/generateFirstWithProject", method = RequestMethod.GET)
    public String generateFirstWP(@RequestParam Long taskId, @RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        PromotionRequest newPromotionRequest = new PromotionRequest();
        newPromotionRequest.setComments("Promovido desde desarrollo");
        newPromotionRequest.setDateCreated(new Date());
        newPromotionRequest.setProject(project);
        promotionRequestServiceImpl.savePromotionRequest(newPromotionRequest);
        Setup setup = setupServiceImpl.findSetup(1L);
        ArtifactoryClient artifactoryClient = new ArtifactoryClient(setup.getArtifactoryHost(),Integer.parseInt(setup.getArtifactoryPort()), setup.getArtifactoryUsername(), setup.getArtifactoryPassword());
        List<String> versions = artifactoryClient.getVersions(project.getGroupId().replaceAll("\\.","/"), project.getName());
        model.addAttribute("versions",versions);
        model.addAttribute("taskId",taskId);
        model.addAttribute("promotion", newPromotionRequest);
        return "showPromotion"; 
    }
    
    @RequestMapping(value = "/promoteToNextLevel", method = RequestMethod.POST)
    public String nextLevel(HttpSession session, @RequestParam String version, @RequestParam String comments, @RequestParam String outcome, @RequestParam Long projectId, @RequestParam Long taskId, ModelMap model) {
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
        parametro.setValor(version);
        parametros.add(parametro);
        parametro = new Parametro();
        parametro.setLlave("comentarios_");
        parametro.setValor(comments);
        parametros.add(parametro);
        String rol = sessionServiceImpl.getRole(session);
        Environment environment = null;
        for(Environment e : project.getEnvironments()){
            if(rol.equals(e.getName())){
                environment = e;
            }
        }
        parametro = new Parametro();
        parametro.setLlave("jbossHost_");
        parametro.setValor(environment.getHost());
        parametro.setLlave("jbossPort_");
        parametro.setValor(environment.getPort());
        parametro.setLlave("jbossUser_");
        parametro.setValor(environment.getUsername());
        parametro.setLlave("jbossPassword_");
        parametro.setValor(environment.getPassword());
        parametros.add(parametro);
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId(session.getAttribute("role").toString());
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
        Setup setup = setupServiceImpl.findSetup(1L);
        ArtifactoryClient artifactoryClient = new ArtifactoryClient(setup.getArtifactoryHost(),Integer.parseInt(setup.getArtifactoryPort()), setup.getArtifactoryUsername(), setup.getArtifactoryPassword());
        List<String> versions = artifactoryClient.getVersions(project.getGroupId().replaceAll("\\.","/"), nombreProyecto);
        model.addAttribute("promotion", promotionRequest);
        model.addAttribute("taskId",taskId);
        model.addAttribute("versions",versions);
        return "showPromotion";  
    }
    
}