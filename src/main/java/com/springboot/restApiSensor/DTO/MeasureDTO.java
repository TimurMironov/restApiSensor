package com.springboot.restApiSensor.DTO;

import com.springboot.restApiSensor.models.Sensor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class MeasureDTO implements Serializable {

    @Min(-100)
    @Max(100)
    private double value;

    @NotEmpty
    private boolean raining;

    @NotEmpty
    private SensorDTO sensor;

    public MeasureDTO() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
