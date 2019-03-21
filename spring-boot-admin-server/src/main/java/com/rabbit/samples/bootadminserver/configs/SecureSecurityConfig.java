package com.rabbit.samples.bootadminserver.configs;

import com.rabbit.samples.bootadminserver.constants.Profiles;
import com.rabbit.samples.bootadminserver.constants.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
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
/*
	WARNING (Solution 1, see Solution 2 in pom.xml): required to solve a conflict problem between Spring Boot Admin Server and Spring Boot Actuator
	org.springframework.beans.factory.BeanCreationException:
		Error creating bean with name 'securityContextConfig': Injection of autowired dependencies failed; nested exception is
		org.springframework.beans.factory.BeanCreationException: Could not autowire method: public void
			org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
				.setObjectPostProcessor(org.springframework.security.config.annotation.ObjectPostProcessor);
			nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type
				[org.springframework.security.config.annotation.ObjectPostProcessor] found for dependency: expected at least 1 bean which qualifies as autowire
				candidate for this dependency. Dependency annotations: {}
*/
@EnableWebSecurity
@Profile(Profiles.SECURE)
public class SecureSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		log.debug("Load SECURE security config...");

		httpSecurity
				.httpBasic()
				.and()

				.authorizeRequests()

				.antMatchers("/assets/**").permitAll()
				.antMatchers("/login").permitAll()
				.anyRequest().hasRole(Roles.ADMIN)

				.and()
				.formLogin().loginPage("/login")
				.successHandler(getSavedRequestAwareAuthenticationSuccessHandler())

				.and()
				.logout().logoutUrl("/logout")

				.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers("/instances", "/actuator/**")
				// .csrf()
				// .csrfTokenRepository(csrfTokenRepository())
				// .and()
				// .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)

		;
	}

	private SavedRequestAwareAuthenticationSuccessHandler getSavedRequestAwareAuthenticationSuccessHandler() {

		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl("/");
		return successHandler;
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
