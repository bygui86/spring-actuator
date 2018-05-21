package com.rabbitshop.springactuatorsample.endpoints;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter(value = AccessLevel.PROTECTED)
@Component
@Endpoint(id = "features", enableByDefault = false)
public class FeaturesEndpoint {

	Map<String, LogLevel> features = new ConcurrentHashMap<>();

	@ReadOperation
	public Map<String, LogLevel> readFeatures() {

		log.debug("Get all features");

		return getFeatures();
	}

	@ReadOperation
	public LogLevel readFeature(@Selector final String name) {

		log.debug("Get feature {}", name);

		return getFeatures().get(name);
	}

	@WriteOperation
	public void writeFeature(@Selector final String name, final LogLevel logLevel) {

		log.debug("Write feature {} with log-level {}", name, logLevel);

		getFeatures().put(name, logLevel);
	}

	@DeleteOperation
	public void deleteFeature(@Selector final String name) {

		log.debug("Delete feature {}", name);

		getFeatures().remove(name);
	}

}
