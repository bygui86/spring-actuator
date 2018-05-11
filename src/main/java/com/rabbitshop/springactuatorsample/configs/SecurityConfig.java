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

	static String ACTUATOR_URL_MATCHER = "/actuator/**";

	static String CUSTOMER_URL_MATCHER = "/custom/**";

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http
				.csrf().ignoringAntMatchers(ACTUATOR_URL_MATCHER, CUSTOMER_URL_MATCHER)
				.and()
				.authorizeRequests()
				.mvcMatchers(ACTUATOR_URL_MATCHER, CUSTOMER_URL_MATCHER).permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}
}
