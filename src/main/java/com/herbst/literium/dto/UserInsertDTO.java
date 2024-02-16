package com.herbst.literium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO extends UserDTO{
    private String password;
    public UserInsertDTO(){
        super();
    }
}
