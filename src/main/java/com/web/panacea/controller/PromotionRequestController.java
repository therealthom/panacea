package com.web.panacea.controller;

import com.web.panacea.domain.PromotionRequest;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/promotion")
public class PromotionRequestController {
            
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
