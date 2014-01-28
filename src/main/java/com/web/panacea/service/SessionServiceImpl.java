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
public class SessionServiceImpl implements SessionService {
    
    public String getRole(HttpSession session){
        return session.getAttribute("role").toString();
    }
    
}
