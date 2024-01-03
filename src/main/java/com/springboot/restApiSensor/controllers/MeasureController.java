package com.springboot.restApiSensor.controllers;

import com.springboot.restApiSensor.DTO.MeasureDTO;
import com.springboot.restApiSensor.exceptions.MeasureException;
import com.springboot.restApiSensor.models.Measure;
import com.springboot.restApiSensor.service.MeasureService;
import com.springboot.restApiSensor.validation.MeasureValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measures")
public class MeasureController {

    private final MeasureService measureService;
    private final MeasureValidator measureValidator;

    public MeasureController(MeasureService measureService, MeasureValidator measureValidator) {
        this.measureService = measureService;
        this.measureValidator = measureValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewMeasure(@RequestBody @Valid MeasureDTO measureDTO
                                                    , BindingResult bindingResult){
        Measure measure = measureService.convertFromDTO(measureDTO);
        measureValidator.validate(measure, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";\n");
            }

            throw new MeasureException(errorMessage.toString());
        }

        measureService.saveMeasure(measure);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/measurements")
    public List<MeasureDTO> allMeasures(){
        return measureService.getAllMeasures();
    }

    @GetMapping("/measurements/rainyDaysCount")
    public int rainyDaysCount(){
        return measureService.rainyDaysCount();
    }
}
