package com.springboot.restApiSensor.service;

import com.springboot.restApiSensor.DTO.MeasureDTO;
import com.springboot.restApiSensor.models.Measure;
import com.springboot.restApiSensor.models.Sensor;
import com.springboot.restApiSensor.repository.MeasureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasureService {

    private final MeasureRepository measureRepository;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    public MeasureService(MeasureRepository measureRepository, ModelMapper modelMapper, SensorService sensorService) {
        this.measureRepository = measureRepository;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @Transactional
    public void saveMeasure(Measure measure){
        Sensor sensor = sensorService.getAllSensorsByName(measure.getSensor().getName()).stream().findAny().get();
        sensor.addMeasureToList(measure);
        sensorService.saveSensor(sensor);
    }

    public List<MeasureDTO> getAllMeasures(){
        List<Measure> measureList = measureRepository.findAll();
        List<MeasureDTO> measureDTOList = new ArrayList<>();

        for (Measure measure:measureList){
            MeasureDTO measureDTO = convertToDTO(measure);
//            modelMapper.map(measureDTO.getSensor(), SensorDTO.class);
            measureDTOList.add(measureDTO);
        }
        return measureDTOList;
    }


    //-----------Converter------------//

    public Measure convertFromDTO(MeasureDTO measureDTO){
        return modelMapper.map(measureDTO, Measure.class);
    }

    public MeasureDTO convertToDTO(Measure measure){
        return modelMapper.map(measure, MeasureDTO.class);
    }

    //--------Rainy Days Count------//

    public int rainyDaysCount(){
        List<Measure> measureList = measureRepository.findAll();
        int rainyDaysCount=0;

        for (Measure measure : measureList) {
            if(measure.isRaining()){
                rainyDaysCount++;
            }
        }
        return rainyDaysCount;
    }
}
