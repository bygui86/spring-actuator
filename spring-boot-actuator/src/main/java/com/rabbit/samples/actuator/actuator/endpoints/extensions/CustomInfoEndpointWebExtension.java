package com.rabbit.samples.actuator.actuator.endpoints.extensions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class CustomInfoEndpointWebExtension {

	static final String INFO_MAP_APP = "app";

	static final String INFO_MAP_APP_VERSION = "version";

	static final String SNAPSHOT_KEYWORD = "snapshot";

	static final int RESPONSE_STATUS_SERVERERROR = 500;

	static final int RESPONSE_STATUS_OK = 200;

	InfoEndpoint infoEndpoint;

	@ReadOperation
	public WebEndpointResponse<Map> info() {

		log.debug("Custom info endpoint extension...");

		final Map<String, Object> info = getInfoEndpoint().info();
		final Integer status = getStatus(info);
		return new WebEndpointResponse<>(info, status);
	}

	private Integer getStatus(final Map<String, Object> info) {

		info.forEach((key, value) -> log.debug("Info entry key[{}] value[{}]", key, value));

		final Object appObj = info.get(INFO_MAP_APP);
		if (appObj != null) {
			final Map appMap = (Map) appObj;
			final Object versionObj = appMap.get(INFO_MAP_APP_VERSION);
			if (versionObj instanceof String) {
				final String version = (String) versionObj;
				if (isSnapshot(version)) {
					return RESPONSE_STATUS_SERVERERROR;
				}
			}
		}

		return RESPONSE_STATUS_OK;
	}

	private boolean isSnapshot(final String version) {

		return version.toLowerCase().contains(SNAPSHOT_KEYWORD);
	}

}