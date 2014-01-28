package com.web.panacea.service;

import javax.servlet.http.HttpSession;

/**
 *
 * @author oscar
 */
public interface SessionService {
    
    public abstract String getRole(HttpSession session);
    
}
