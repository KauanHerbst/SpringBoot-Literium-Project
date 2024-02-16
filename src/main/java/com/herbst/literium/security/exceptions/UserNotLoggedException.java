package com.herbst.literium.security.exceptions;

public class UserNotLoggedException extends RuntimeException {
    public UserNotLoggedException(String message){
        super(message);
    }
}
