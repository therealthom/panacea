package com.web.panacea.controller;

import com.web.panacea.domain.Document;
import com.web.panacea.domain.PromotionRequest;
import com.web.panacea.service.DocumentService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentServiceImpl;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDocument(@RequestParam Long taskId, @RequestParam Long promotionId, ModelMap model) {
        model.addAttribute("promotionId", promotionId);
        model.addAttribute("taskId", taskId);
        return "newDocument";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadDocument(@RequestParam Long taskId, @RequestParam MultipartFile file, @RequestParam Long promotionId, Model model) throws IOException {
        System.out.println("filename >>> " + file.getOriginalFilename());
        System.out.println("size >>> " + file.getSize());
        Document document = new Document();
        document.setPromotionRequest(PromotionRequest.findPromotionRequest(promotionId));
        document.setFilename(file.getOriginalFilename());
        document.setFile(file.getBytes());
        documentServiceImpl.saveDocument(document);
        return "redirect:/promotion/show?promotionId=" + promotionId + "&taskId=" + taskId;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download(@RequestParam Long documentId, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        Document document = documentServiceImpl.findDocument(documentId);
        response.setContentLength(document.getFile().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getFilename() +"\"");
        FileCopyUtils.copy(document.getFile(), response.getOutputStream());
        return null; 
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Long taskId, @RequestParam Long documentId, @RequestParam Long promotionId) {
        Document document = documentServiceImpl.findDocument(documentId);
        documentServiceImpl.deleteDocument(document);        
        return "redirect:/promotion/show?promotionId=" + promotionId + "&taskId=" + taskId;
    }
}
