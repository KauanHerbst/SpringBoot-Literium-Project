package com.herbst.literium.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @Email(message = "Email precisa ser um email válido")
    @NotBlank(message = "Email não pode ser vázio")
    private String email;
    @NotBlank(message = "Password não pode ser vázio")
    private String password;

}
