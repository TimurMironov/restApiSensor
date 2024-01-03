package com.springboot.restApiSensor.DTO;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public class SensorDTO implements Serializable {

    @NotEmpty(message = "name should not be empty")
    private String name;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
