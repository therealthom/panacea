package com.web.panacea.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    
    public String getRole(HttpSession session){
        return session.getAttribute("role").toString();
    }
    
}
