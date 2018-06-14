package com.rabbitshop.springactuatorsample.configs;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@Order(100)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	static String ACTUATOR_DEFAULT_URL_MATCHER = "/actuator/**";

	static String DOMAIN_URL_MATCHER = "/catalog/**";

	static String NOTIFICATION_URL_MATCHER = "/mail/**";

	/*
	 * PLEASE NOTE: For sake of simplicity we are omitting specific security configurations
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		log.debug("Loading INSECURE security config...");

		http
				.authorizeRequests()
				.mvcMatchers(ACTUATOR_DEFAULT_URL_MATCHER).permitAll()
				.mvcMatchers(DOMAIN_URL_MATCHER, NOTIFICATION_URL_MATCHER).permitAll()
				.anyRequest().permitAll()

				.and()
				.httpBasic()

				.and()
				.cors().disable()
				.csrf().disable();
	}

}
