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

import com.web.panacea.domain.Setup;
import com.web.panacea.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    SetupService setupServiceImpl;
    
    @RequestMapping(value = "/promotionRequestInbox", method = RequestMethod.GET)
    public String promotionRequestInbox() {
        return "promotionRequestInbox";
    }
            
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        //return "redirect:/home/user";
        return "dashboard";
    }
}