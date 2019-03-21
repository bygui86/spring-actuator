package com.rabbit.samples.actuator.configs;

import com.rabbit.samples.actuator.constants.Endpoints;
import com.rabbit.samples.actuator.constants.Profiles;
import com.rabbit.samples.actuator.constants.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Slf4j
@Configuration
@Profile(Profiles.SECURE)
public class SecureSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {

		log.debug("Load SECURE security config...");

		httpSecurity
				.authorizeRequests()
				.mvcMatchers(Endpoints.ACTUATOR_MATCHER).hasRole(Roles.ACTUATOR)
				.anyRequest().authenticated()
				.mvcMatchers(Endpoints.MAIL_MATCHER, Endpoints.DOMAIN_MATCHER).permitAll()
				.anyRequest().permitAll()

				.and()
				.httpBasic()

				.and()
				.cors().disable()
				.csrf().disable();
	}

}
