package com.rabbit.samples.actuator.actuator.contributors;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 20 Mar 2019
 */
@Getter(AccessLevel.PROTECTED)
@Component
public class CustomInfoContributors implements InfoContributor {

	@Value("${custom.actuator.info.contribution.random}")
	String customInfoContributionRandom;

	@Value("${custom.actuator.info.contribution.key}")
	String customInfoContributionKey;

	@Value("${custom.actuator.info.contribution.value}")
	String customInfoContributionValue;

	@Override
	public void contribute(final Info.Builder builder) {

		builder
				.withDetail("contribution-random",
						getCustomInfoContributionRandom())
				.withDetail("contribution-map",
						Collections.singletonMap(getCustomInfoContributionKey(), getCustomInfoContributionValue()))
		;
	}

}
