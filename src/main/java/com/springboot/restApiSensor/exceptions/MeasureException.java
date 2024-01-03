package com.springboot.restApiSensor.exceptions;

public class MeasureException extends RuntimeException{

    public MeasureException(String message) {
        super(message);
    }
}
