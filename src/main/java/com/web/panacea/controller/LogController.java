package com.web.panacea.controller;

import com.web.panacea.domain.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/log")
public class LogController {
            
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        
        //Mock
        List<Log> logs = new ArrayList<Log>();
        Log log1 = new Log();
        log1.setDescription("asdasd");
        log1.setUsername("asas");
        log1.setDateCreated(new Date());
        Log log2 = new Log();
        log2.setDescription("asdasd");
        log2.setUsername("asas");
        log2.setDateCreated(new Date());
        Log log3 = new Log();
        log3.setDescription("asdasd");
        log3.setUsername("asas");
        log3.setDateCreated(new Date());
        
        logs.add(log1);
        logs.add(log2);
        logs.add(log3);
        
        model.addAttribute("logs", logs);
        
        return "listLog";
    }
}
