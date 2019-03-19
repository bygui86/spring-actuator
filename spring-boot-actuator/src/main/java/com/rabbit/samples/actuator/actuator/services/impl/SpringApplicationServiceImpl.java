package com.rabbit.samples.actuator.actuator.services.impl;

import com.rabbit.samples.actuator.actuator.services.SpringApplicationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Service
public class SpringApplicationServiceImpl implements SpringApplicationService {

	ConfigurableApplicationContext configurableApplicationContext;

	@Override
	public ConfigurableApplicationContext getConfigurableApplicationContext() {

		log.debug("Get ConfigurableApplicationContext...");

		return getConfigurableApplicationContext();
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
				SpringApplication.exit(getConfigurableApplicationContext(), () -> 0)
		);
	}

}
