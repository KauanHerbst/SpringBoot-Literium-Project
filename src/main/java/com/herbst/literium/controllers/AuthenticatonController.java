package com.herbst.literium.controllers;

import com.herbst.literium.dto.LoginResponseDTO;
import com.herbst.literium.dto.TokenValidDTO;
import com.herbst.literium.dto.UserLoginDTO;
import com.herbst.literium.security.exceptions.UserNotLoggedException;
import com.herbst.literium.services.AuthenticatonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("auth")
public class AuthenticatonController {
    @Autowired
    private AuthenticatonService authenticatonService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO entityUserLogin){
        try{
            return ResponseEntity.ok(authenticatonService.login(entityUserLogin));
        }catch (NullPointerException e){
            throw new UserNotLoggedException("User Not Logged");
        }
    }

    @PostMapping("/valid")
    public ResponseEntity validToken(@RequestBody TokenValidDTO tokenValidDTO){
        if(authenticatonService.validToken(tokenValidDTO)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
