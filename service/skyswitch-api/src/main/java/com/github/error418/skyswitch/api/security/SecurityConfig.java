package com.github.error418.skyswitch.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String SKYSWITCH_API_AUTHORITY = "SKYSWITCH_API";
	
	private final TokenAuthenticationProvider authenticationProvider;

	@Autowired
	public SecurityConfig(TokenAuthenticationProvider tokenAuthenticationProvider) {
		this.authenticationProvider = tokenAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/skyswitch/**")
		.authorizeRequests()
		.anyRequest()
		.hasAnyAuthority(SKYSWITCH_API_AUTHORITY)
		.and()
		.addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class)
		.authenticationProvider(this.authenticationProvider)
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf();
	}
}
