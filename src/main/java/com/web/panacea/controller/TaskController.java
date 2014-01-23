/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.web.panacea.controller;

import java.util.List;
import mx.redhat.brms.ws.tareas.impl.HumanTaskService;
import mx.redhat.brms.ws.tareas.impl.HumanTaskServiceService;
import mx.redhat.brms.ws.tareas.impl.TaskSummary;
import mx.redhat.brms.ws.tareas.impl.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author oscar
 */

@Controller
@RequestMapping("/task")
public class TaskController {
    
    @RequestMapping(value = "/taskTray", method = RequestMethod.GET)
    public String taskTray(ModelMap model) {
        HumanTaskServiceService hts = new HumanTaskServiceService();
        HumanTaskService service = hts.getHumanTaskServicePort();
        User user = new User();
        user.setId("admin");
        List<TaskSummary> tareas = service.obtenerTareasGrupos(user, null);
        model.addAttribute("tareas", tareas);
        return "taskTray";
    }
}
