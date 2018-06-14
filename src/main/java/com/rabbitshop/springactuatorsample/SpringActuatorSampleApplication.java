package com.rabbitshop.springactuatorsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


// Spring Boot performances: remove @SpringBootApplication, replace with @EnableAutoConfiguration and @ComponentScan
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rabbitshop"})
public class SpringActuatorSampleApplication {

	public static void main(final String[] args) {

		SpringApplication.run(SpringActuatorSampleApplication.class, args);
	}

}
