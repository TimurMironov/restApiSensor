package com.springboot.restApiSensor.repository;

import com.springboot.restApiSensor.models.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure, Integer> {

}
