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
import com.web.panacea.service.ProjectService;
import com.web.panacea.service.PromotionRequestService;
import java.util.Date;
import java.util.List;
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
    
    @RequestMapping(value = "/createFirstPromotionRequest", method = RequestMethod.GET)
    public String createFirst(@RequestParam Long projectId, ModelMap model) {
        Project project = projectServiceImpl.findProject(projectId);
        model.addAttribute("project",project);
        PromotionRequest promotionRequest = new PromotionRequest();
        model.addAttribute("promotionRequest", promotionRequest);
        return "createFirstPromotionRequest";
    }
    
    @RequestMapping(value = "/saveFirstPromotionRequest", method = RequestMethod.POST)
    public String saveFirst(@RequestParam Long projectId, ModelMap model, PromotionRequest promotionRequest) {
        Project project = projectServiceImpl.findProject(projectId);
        PromotionRequest newPromotionRequest = new PromotionRequest();
        newPromotionRequest.setComments(promotionRequest.getComments());
        newPromotionRequest.setDateCreated(new Date());
        newPromotionRequest.setProject(project);
        promotionRequestServiceImpl.savePromotionRequest(newPromotionRequest);
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
    public String show(@RequestParam Long promotionId, ModelMap model) {
        PromotionRequest promotion = PromotionRequest.findPromotionRequest(promotionId);
        model.addAttribute("promotion", promotion);
        return "showPromotion";   
    }
}