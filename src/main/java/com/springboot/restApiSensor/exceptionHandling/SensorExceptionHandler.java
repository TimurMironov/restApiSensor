package com.springboot.restApiSensor.exceptionHandling;

import com.springboot.restApiSensor.exceptions.ExceptionResponseJSON;
import com.springboot.restApiSensor.exceptions.SensorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SensorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseJSON> sensorExceptionHandler(SensorException sensorException){
        ExceptionResponseJSON response = new ExceptionResponseJSON();

        response.setMessage(sensorException.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


    }
}
