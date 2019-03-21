package com.rabbit.samples.actuator.spring.controllers;

import com.rabbit.samples.actuator.spring.services.SpringApplicationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping("/admin")
public class SpringApplicationController {

	@Value("${spring.application.name}")
	String applicationName;

	SpringApplicationService springApplicationService;

	public SpringApplicationController(final SpringApplicationService springApplicationService) {

		this.springApplicationService = springApplicationService;
	}

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
	 * Alternative to Boot Actuator shutdown endpoint
	 */
	@PostMapping("/shutdown")
	public void shutdown() {

		log.warn("Shut down application...");

		getSpringApplicationService().shutdownApplication();
	}

}
