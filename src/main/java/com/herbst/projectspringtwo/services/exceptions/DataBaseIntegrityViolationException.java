package com.herbst.projectspringtwo.services.exceptions;

public class DataBaseIntegrityViolationException extends RuntimeException{
    public DataBaseIntegrityViolationException(Object id){
        super("Integrity Violation Id: " + id);
    }
    public DataBaseIntegrityViolationException(){
        super("Integrity Violation");
    }
}
