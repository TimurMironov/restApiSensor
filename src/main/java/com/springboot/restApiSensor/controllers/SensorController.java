package com.springboot.restApiSensor.controllers;

import com.springboot.restApiSensor.DTO.SensorDTO;
import com.springboot.restApiSensor.exceptions.SensorException;
import com.springboot.restApiSensor.models.Sensor;
import com.springboot.restApiSensor.service.SensorService;
import com.springboot.restApiSensor.validation.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult){
            //Конвертация из DTO
            Sensor sensor = sensorService.convertFromDTO(sensorDTO);

            //Собственная валидация (проверка на наличие в БД сенсора с переданным названием)
            sensorValidator.validate(sensor, bindingResult);

            //Объединяет все ошибки в StringBuilder, выбрасывает исключение, которое потом ловит SensorExceptionHandler
            if(bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();

                StringBuilder errorMessage = new StringBuilder();
                for (FieldError error : errors){
                     errorMessage.append(error.getField())
                             .append(" - ")
                             .append(error.getDefaultMessage())
                             .append(";\n");
                }
                throw new SensorException(errorMessage.toString());
            }

            // Сохранение в БД, если ошибки отсутствуют
            sensorService.saveSensor(sensor);

            return ResponseEntity.ok(HttpStatus.OK);
    }

}
