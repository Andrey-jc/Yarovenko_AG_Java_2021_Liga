package com.example.liquibasedemo.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity onNotFound(Exception exception) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
