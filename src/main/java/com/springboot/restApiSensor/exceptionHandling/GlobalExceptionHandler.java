package com.springboot.restApiSensor.exceptionHandling;

import com.springboot.restApiSensor.exceptions.ExceptionResponseJSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseJSON> globalExceptionHandler(Exception globalException){
        ExceptionResponseJSON exceptionResponseJSON = new ExceptionResponseJSON();

        exceptionResponseJSON.setMessage(globalException.getMessage());
        return new ResponseEntity<>(exceptionResponseJSON, HttpStatus.BAD_REQUEST);
    }
}
