package com.herbst.projectspringtwo.dto;

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
