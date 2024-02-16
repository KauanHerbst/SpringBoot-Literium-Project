package com.herbst.literium.services.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(){
        super("Entity Already Exists");
    }
}
