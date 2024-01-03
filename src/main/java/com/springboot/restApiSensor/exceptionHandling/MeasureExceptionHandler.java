package com.springboot.restApiSensor.exceptionHandling;

import com.springboot.restApiSensor.exceptions.ExceptionResponseJSON;
import com.springboot.restApiSensor.exceptions.MeasureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeasureExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseJSON> measureExceptionHandler(MeasureException measureException){
        ExceptionResponseJSON response = new ExceptionResponseJSON();

        response.setMessage(measureException.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
