package com.rabbit.samples.actuator.actuator.indicators;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 20 Mar 2019
 */
@Getter(AccessLevel.PROTECTED)
@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Value("${custom.actuator.health.indicator.counter}")
	int counter;

	@Override
	public Health health() {

		return Health.up().withDetail("counter", getCounter()).build();
	}

}
