package com.rabbitshop.springactuatorsample.actuator.services.impl;

import com.rabbitshop.springactuatorsample.actuator.services.SpringApplicationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;


@Slf4j
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PROTECTED)
@Service("springApplicationService")
public class SpringApplicationServiceImpl implements SpringApplicationService {

	@Autowired
	ConfigurableApplicationContext configAppCtx;

	@Override
	public ConfigurableApplicationContext getConfigurableApplicationContext() {

		log.debug("Get ConfigurableApplicationContext...");

		return getConfigAppCtx();
	}

	/**
	 * Shutdown the application.
	 * PLEASE NOTE: there is no need to call explicitly the method {@link ConfigurableApplicationContext#close()}, this will be invoked
	 * from the method {@link SpringApplication#exit(ApplicationContext, ExitCodeGenerator...)}
	 */
	@Override
	public void shutdownApplication() {

		log.warn("Shutting application down...");
		System.exit(
				SpringApplication.exit(getConfigAppCtx(), () -> 0)
		);
	}

}
