package com.herbst.projectspringtwo.controllers;

import com.herbst.projectspringtwo.dto.LoginResponseDTO;
import com.herbst.projectspringtwo.dto.UserLoginDTO;
import com.herbst.projectspringtwo.security.exceptions.UserNotLoggedException;
import com.herbst.projectspringtwo.services.AuthenticatonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticatonController {
    @Autowired
    private AuthenticatonService authenticatonService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO entityUserLogin){
        try{
            var Token = authenticatonService.login(entityUserLogin);
            return ResponseEntity.ok(new LoginResponseDTO(Token));
        }catch (NullPointerException e){
            throw new UserNotLoggedException("User Not Logged");
        }
    }
}
