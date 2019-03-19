package com.rabbit.samples.actuator.actuator.controllers;

import com.rabbit.samples.actuator.actuator.services.SpringApplicationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping("/admin")
public class SpringActuatorCustomController {

	@Value("${spring.application.name}")
	String applicationName;

	SpringApplicationService springApplicationService;

	@PreDestroy
	public void onDestroy() {

		log.warn("Spring Container destruction started...");
	}

	@GetMapping("/name")
	public String getAppName() {

		log.debug("Get application name...");

		return getApplicationName();
	}

	/**
	 * PLEASE NOTE: there is no need to specify neither a return statement nor a ResponseStatus, because the application will terminate almost immidiately.
	 */
	@PostMapping("/shutdown")
	public void shutdown() {

		log.warn("Shut down application...");

		getSpringApplicationService().shutdownApplication();
	}

}
