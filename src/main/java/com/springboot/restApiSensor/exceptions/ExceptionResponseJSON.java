package com.springboot.restApiSensor.exceptions;

public class ExceptionResponseJSON {

    private String message;

    public ExceptionResponseJSON() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
