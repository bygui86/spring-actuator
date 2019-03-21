package com.rabbit.samples.bootadminserver.configs;

import com.rabbit.samples.bootadminserver.constants.Profiles;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@Profile(Profiles.INSECURE)
public class InsecureSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {

		log.warn("Load INSECURE security config...");

		httpSecurity
				.authorizeRequests()
				.anyRequest().permitAll()

				.and()
				.httpBasic()

				.and()
				.cors().disable()
				.csrf().disable();
	}

}
