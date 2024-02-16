package com.herbst.literium.services;

import com.herbst.literium.dto.LoginResponseDTO;
import com.herbst.literium.dto.TokenValidDTO;
import com.herbst.literium.dto.UserLoginDTO;
import com.herbst.literium.entities.User;
import com.herbst.literium.repositories.UserRepository;
import com.herbst.literium.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class AuthenticatonService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDTO login(UserLoginDTO entityUserLogin){
        var userNamePassword = new UsernamePasswordAuthenticationToken(entityUserLogin.getEmail(), entityUserLogin.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        User user = userRepository.findByEmail(entityUserLogin.getEmail());
        return new LoginResponseDTO(token, user);
    }

    public Boolean validToken(TokenValidDTO tokenValidDTO){
        var token = tokenValidDTO.getToken();
        var email = tokenService.validateToken(token);
        UserDetails userDetails = userRepository.findByEmail(email);
        if(userDetails != null){
            return true;
        }
        return false;
    }
}
