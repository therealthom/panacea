package com.web.panacea.service;

import com.web.panacea.domain.Login;

/**
 *
 * @author thom
 */
public interface LoginService {

    public abstract Login findLoginByUsernameAndPassword(String username, String password);
}
