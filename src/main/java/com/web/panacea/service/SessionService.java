/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.web.panacea.service;

import javax.servlet.http.HttpSession;

/**
 *
 * @author oscar
 */
public interface SessionService {
    
    public abstract String getRole(HttpSession session);
    
}
