package com.rabbitshop.springactuatorsample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.rabbitshop.springactuatorsample"})
public class SpringActuatorSampleApplication {

	public static void main(final String[] args) {

		SpringApplication.run(SpringActuatorSampleApplication.class, args);
	}

}
