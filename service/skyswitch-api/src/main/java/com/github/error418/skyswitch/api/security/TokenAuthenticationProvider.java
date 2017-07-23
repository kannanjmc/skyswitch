package com.github.error418.skyswitch.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.github.error418.skyswitch.api.service.ApiTokenService;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
	
	private Logger log = LoggerFactory.getLogger(TokenAuthenticationProvider.class);

	@Autowired
	private ApiTokenService tokenService;
	
	public TokenAuthenticationProvider() {
		log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("authenticating credentials...");
		
		
		String token = (authentication.getCredentials() == null) ? null : authentication.getCredentials().toString();
		log.info("got token {} :: validated {}", token, tokenService.isValid(token));
		if(token != null && tokenService.isValid(token)) {
			AuthenticationToken authenticatedToken = new AuthenticationToken(token, "SKYSWITCH_API");
			authenticatedToken.setAuthenticated(true);
			return authenticatedToken;
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		log.info("comparing {} :: {}", authentication, AuthenticationToken.class);
		return true;
//		return AuthenticationToken.class.equals(authentication);
	}

}
