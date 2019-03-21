package com.rabbit.samples.actuator.spring.services;

import org.springframework.context.ConfigurableApplicationContext;


public interface SpringApplicationService {

	ConfigurableApplicationContext getConfigurableApplicationContext();

	void shutdownApplication();

}
