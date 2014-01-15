package com.web.panacea.controller;

import com.web.panacea.domain.PromotionRequest;
import java.util.ArrayList;
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
        List<PromotionRequest> promotions = new ArrayList<PromotionRequest>();
        PromotionRequest pr1 = new PromotionRequest();
        pr1.setId(1L);
        PromotionRequest pr2 = new PromotionRequest();
        pr2.setId(2L);
        PromotionRequest pr3 = new PromotionRequest();
        pr3.setId(3L);
        promotions.add(pr1);
        promotions.add(pr2);
        promotions.add(pr3);
        model.addAttribute("promotions", promotions);
        return "listPromotion";
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam Long promotionId, ModelMap model) {
        PromotionRequest promotion = new PromotionRequest();
        promotion.setId(promotionId);
        model.addAttribute("promotion", promotion);
        return "showPromotion";
    }
}
