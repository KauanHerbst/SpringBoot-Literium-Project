package com.herbst.projectspringtwo.controllers.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.herbst.projectspringtwo.security.exceptions.UserNotLoggedException;
import com.herbst.projectspringtwo.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.projectspringtwo.services.exceptions.EntityIdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityIdNotFoundException.class)
    @ResponseBody
    public ResponseEntity<StandardError> entityNotFound(EntityIdNotFoundException e, HttpServletRequest request){
        String error = "Entity Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(error, Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DataBaseIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<StandardError> dataBaseIntegrityViolation(DataBaseIntegrityViolationException e, HttpServletRequest request){
        String error = "Integrity Violation";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(error, Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public ResponseEntity<StandardError> tokenExpiredException(TokenExpiredException e, HttpServletRequest request){
        String error = "Token Expired";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(error, Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    @ResponseBody
    public ResponseEntity<StandardError> invalidBearerTokenException(InvalidBearerTokenException e, HttpServletRequest request){
        String error = "Invalid Token";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(error, Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseBody
    public ResponseEntity<StandardError> accountStatusException(AccountStatusException e, HttpServletRequest request){
        String error = "User account is abnormal";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError err = new StandardError(error, Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<StandardError> handleOtherException(Exception e, HttpServletRequest request){
        String error = "Internal Server Error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(error, Instant.now(), e.getMessage(), status.value(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
