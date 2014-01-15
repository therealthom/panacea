package com.web.panacea.controller;

import com.web.panacea.domain.Document;
import com.web.panacea.domain.PromotionRequest;
import com.web.panacea.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentServiceImpl;
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDocument(@RequestParam Long promotionId, ModelMap model) {
        Document document = new Document();
        document.setPromotionRequest(PromotionRequest.findPromotionRequest(promotionId));
        model.addAttribute("document", document);
        return "newDocument";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadDocument(Document document, ModelMap model) {
        System.out.println("document >>> " + document.getName());      
        documentServiceImpl.saveDocument(document);
        return "redirect:/promotion/show?promotionId=" + document.getPromotionRequest().getId();
    }
}
