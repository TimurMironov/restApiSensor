package com.springboot.restApiSensor.validation;


import com.springboot.restApiSensor.models.Sensor;
import com.springboot.restApiSensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        Optional<Sensor> sameNameSensor = sensorService.getAllSensorsByName(sensor.getName()).stream().findAny();

        if(sameNameSensor.isPresent()){
            errors.rejectValue("name", "", "Sensor with name " + sensor.getName() + " already exists");
        }
    }
}
