package com.rabbit.samples.actuator.configs;

import com.rabbit.samples.actuator.constants.Endpoints;
import com.rabbit.samples.actuator.constants.Profiles;
import com.rabbit.samples.actuator.constants.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Configuration
// WARNING: required for better compatibility between Spring Boot Admin and Spring Boot Actuator
@EnableWebSecurity
@Profile(Profiles.SECURE)
public class SecureSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {

		log.debug("Load SECURE security config...");

		httpSecurity
				.httpBasic()
				.and()

				.authorizeRequests()
				.mvcMatchers(Endpoints.ACTUATOR_MATCHER).hasRole(Roles.ACTUATOR)
				.anyRequest().authenticated()
				.mvcMatchers(Endpoints.MAIL_MATCHER, Endpoints.DOMAIN_MATCHER).permitAll()
				.anyRequest().permitAll()

				.and()
				.cors().disable()

				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers("/actuator/**")
				// .csrf()
				// .csrfTokenRepository(csrfTokenRepository())
				// .and()
				// .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
		;
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	private Filter csrfHeaderFilter() {

		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(
					HttpServletRequest request,
					HttpServletResponse response,
					FilterChain filterChain)
					throws ServletException, IOException {

				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null
							|| token != null
							&& !token.equals(cookie.getValue())) {

						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}

				filterChain.doFilter(request, response);
			}
		};
	}

}
