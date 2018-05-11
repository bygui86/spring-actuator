package com.rabbitshop.springactuatorsample.services;

import org.springframework.context.ConfigurableApplicationContext;


public interface SpringApplicationService {

	ConfigurableApplicationContext getConfigurableApplicationContext();

	void shutdownApplication();

}
