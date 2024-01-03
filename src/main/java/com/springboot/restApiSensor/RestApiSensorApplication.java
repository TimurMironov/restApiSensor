package com.springboot.restApiSensor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiSensorApplication.class, args);
	}

	@Bean
	public ModelMapper createModelMapper(){
		return new ModelMapper();
	}
}
