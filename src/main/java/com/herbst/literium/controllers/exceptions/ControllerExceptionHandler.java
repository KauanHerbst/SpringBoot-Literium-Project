package com.herbst.literium.controllers.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.herbst.literium.services.exceptions.DataBaseIntegrityViolationException;
import com.herbst.literium.services.exceptions.EntityAlreadyExistsException;
import com.herbst.literium.services.exceptions.EntityIdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler{
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

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<StandardError> entityAlreadyExists(EntityAlreadyExistsException e, HttpServletRequest request){
        String error = "Entity already exists";
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ValidationErrors> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<StandardError> errors = new ArrayList<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldErrorName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            StandardError err = new StandardError(fieldErrorName, Instant.now(), errorMessage, status.value(), request.getRequestURI());
            errors.add(err);
        } );
        return ResponseEntity.status(status).body(new ValidationErrors(errors));
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
