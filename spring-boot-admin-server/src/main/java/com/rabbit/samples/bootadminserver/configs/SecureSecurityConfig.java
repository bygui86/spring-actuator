package com.rabbit.samples.bootadminserver.configs;

import com.rabbit.samples.bootadminserver.constants.Profiles;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Slf4j
// @FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@Profile(Profiles.SECURE)
public class SecureSecurityConfig extends WebSecurityConfigurerAdapter {

	// String adminContextPath;

	// public SecureSecurityConfig(final AdminServerProperties adminServerProperties) {
	//
	// 	this.adminContextPath = adminServerProperties.getContextPath();
	// }

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {

		log.debug("Load SECURE security config...");
		// log.warn("Admin Context Path: " + getAdminContextPath());

		httpSecurity
				.authorizeRequests()
				// .mvcMatchers(Endpoints.ACTUATOR_MATCHER, getAdminContextPath() + "/assets/**", getAdminContextPath() + "/login").hasRole(Roles.ADMIN)
				.anyRequest().authenticated()

				// .and()
				// .formLogin().loginPage(getAdminContextPath() + "/login").successHandler(getSavedRequestAwareAuthenticationSuccessHandler())
				//
				// .and()
				// .logout().logoutUrl(getAdminContextPath() + "/logout")

				.and()
				.httpBasic()

				.and()
				.cors().disable()
				.csrf().disable();
	}

	// private SavedRequestAwareAuthenticationSuccessHandler getSavedRequestAwareAuthenticationSuccessHandler() {
	//
	// 	SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	// 	successHandler.setTargetUrlParameter("redirectTo");
	// 	return successHandler;
	// }

}
