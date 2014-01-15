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
import com.web.panacea.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

    @Autowired
    EnvironmentService environmentServiceImpl;
    
    @RequestMapping(value = "/listEnvironments", method = RequestMethod.GET)
    public String list(ModelMap model) {
        return "listEnvironments";
    }
    
}
