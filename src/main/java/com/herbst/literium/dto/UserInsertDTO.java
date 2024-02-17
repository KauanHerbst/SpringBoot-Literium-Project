package com.herbst.literium.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO extends UserDTO{
    @NotBlank(message = "Password não pode ser vázio")
    private String password;
    public UserInsertDTO(){
        super();
    }
}
