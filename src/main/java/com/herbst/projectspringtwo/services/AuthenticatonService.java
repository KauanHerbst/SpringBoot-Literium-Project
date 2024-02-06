package com.herbst.projectspringtwo.services;

import com.herbst.projectspringtwo.dto.UserLoginDTO;
import com.herbst.projectspringtwo.entities.User;
import com.herbst.projectspringtwo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class AuthenticatonService {
    public static final Logger logger = Logger.getLogger(AuthenticatonService.class.getName());
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(UserLoginDTO entityUserLogin){
        var userNamePassword = new UsernamePasswordAuthenticationToken(entityUserLogin.getEmail(), entityUserLogin.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return tokenService.generateToken((User) auth.getPrincipal());
    }
}
