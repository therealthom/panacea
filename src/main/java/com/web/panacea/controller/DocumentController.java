package com.web.panacea.controller;

import com.web.panacea.domain.Document;
import com.web.panacea.domain.PromotionRequest;
import com.web.panacea.service.DocumentService;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentServiceImpl;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDocument(@RequestParam Long promotionId, ModelMap model) {
        model.addAttribute("promotionId", promotionId);
        return "newDocument";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadDocument(@RequestParam MultipartFile file, @RequestParam Long promotionId, Model model) throws IOException {
        System.out.println("filename >>> " + file.getOriginalFilename());
        System.out.println("size >>> " + file.getSize());
        Document document = new Document();
        document.setPromotionRequest(PromotionRequest.findPromotionRequest(promotionId));
        document.setFilename(file.getOriginalFilename());
        document.setFile(file.getBytes());
        documentServiceImpl.saveDocument(document);
        return "redirect:/promotion/show?promotionId=" + promotionId;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadDocument(@RequestParam Long documentId) {
        try {
            Document document = Document.findDocument(documentId);
            FileOutputStream fos = new FileOutputStream(document.getFilename());
            fos.write(document.getFile());
            fos.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
