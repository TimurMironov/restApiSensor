package com.springboot.restApiSensor.validation;

import com.springboot.restApiSensor.models.Measure;
import com.springboot.restApiSensor.models.Sensor;
import com.springboot.restApiSensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class MeasureValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasureValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measure.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measure measure = (Measure) target;

        Optional<Sensor> sensor = sensorService.getAllSensorsByName(measure.getSensor().getName()).stream().findAny();

        if(sensor.isEmpty()){
            errors.rejectValue("sensor", "", "Sensor's name doesn't exist in data base");
        }
    }
}
