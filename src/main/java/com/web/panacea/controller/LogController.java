package com.web.panacea.controller;

import com.web.panacea.domain.Log;
import com.web.panacea.service.LogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/log")
public class LogController {
           
    @Autowired
    LogService logServiceImpl;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<Log> logs = Log.findAllLogs();
        model.addAttribute("logs", logs);
        return "listLog";
    }
}
