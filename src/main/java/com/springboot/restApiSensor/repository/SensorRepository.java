package com.springboot.restApiSensor.repository;

import com.springboot.restApiSensor.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    List<Sensor> getSensorsByName(String name);
}
