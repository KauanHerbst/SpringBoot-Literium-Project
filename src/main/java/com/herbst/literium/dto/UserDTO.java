package com.herbst.literium.dto;

import com.herbst.literium.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UserDTO {
    private Long id;
    @NotBlank(message = "Campo não pode ser vázio")
    private String name;
    @NotBlank(message = "Email não pode ser vázio")
    @Email(message = "Precisa ser um email valido")
    private String email;
    private List<RoleDTO> roles = new ArrayList<>();
    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
