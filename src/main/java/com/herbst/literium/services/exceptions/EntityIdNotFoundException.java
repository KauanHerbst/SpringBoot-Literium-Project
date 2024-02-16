package com.herbst.literium.services.exceptions;

public class EntityIdNotFoundException extends RuntimeException{
    public EntityIdNotFoundException(Object id){
        super("Entity Not Found ID: " + id);
    }
}
