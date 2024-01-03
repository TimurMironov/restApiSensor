package com.springboot.restApiSensor.service;

import com.springboot.restApiSensor.DTO.SensorDTO;
import com.springboot.restApiSensor.models.Sensor;
import com.springboot.restApiSensor.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void saveSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public List<Sensor> getAllSensorsByName(String name){
        return sensorRepository.getSensorsByName(name);
    }

    //-----------Converter------------//

    public Sensor convertFromDTO(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public SensorDTO convertToDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
