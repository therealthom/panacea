package com.web.panacea.service;

import com.web.panacea.domain.Login;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    public Login findLoginByUsernameAndPassword(String username, String password) {
        return Login.findLoginByUsernameAndPassword(username, password);
    }

}
