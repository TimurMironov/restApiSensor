package com.springboot.restApiSensor.exceptions;

public class SensorException extends RuntimeException{

    public SensorException(String message) {
        super(message);
    }
}
