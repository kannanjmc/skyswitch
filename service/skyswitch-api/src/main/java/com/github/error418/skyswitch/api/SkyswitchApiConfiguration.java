package com.github.error418.skyswitch.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.error418.skyswitch.api.service.token.TokenFileStorage;
import com.github.error418.skyswitch.api.service.token.TokenStorage;


/**
 * Include this Configuration in your application configuration using
 * 
 * <code>@Import(SkyswitchApiConfiguration.class)</code>
 */
@Configuration
@ComponentScan("com.github.error418.skyswitch.api")
public class SkyswitchApiConfiguration {
	private static Logger log = LoggerFactory.getLogger(SkyswitchApiConfiguration.class);

	public SkyswitchApiConfiguration() {
		log.info("Configuring Skyswitch API endpoints...");
	}
	
	@Bean
	public TokenStorage tokenStorageBean()
	{
	    return new TokenFileStorage();
	}
	
}
