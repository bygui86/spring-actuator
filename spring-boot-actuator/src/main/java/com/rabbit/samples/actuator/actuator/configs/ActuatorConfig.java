package com.rabbit.samples.actuator.actuator.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 20 Mar 2019
 */
@Configuration
public class ActuatorConfig {

	@Value("${spring.application.name}")
	String appName;

	@Bean
	public HealthIndicator helloHealthIndicator() {

		return () -> Health.up().withDetail("name", appName).build();
	}

}
