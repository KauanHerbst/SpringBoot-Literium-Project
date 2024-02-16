package com.herbst.literium.dto;

import com.herbst.literium.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String name;
    private Long id;
    private String email;
    private List<RoleDTO> roles = new ArrayList<>();

    public LoginResponseDTO(String token, User user){
        this.token = token;
        this.name = user.getName();
        this.id = user.getId();
        this.email = user.getEmail();
        user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
