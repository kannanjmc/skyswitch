package com.github.error418.skyswitch.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.github.error418.skyswitch.api.service.token.ApiTokenService;


@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
	
	private final ApiTokenService tokenService;

	@Autowired
	public TokenAuthenticationProvider(final ApiTokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (authentication.getCredentials() == null) ? null : authentication.getCredentials().toString();
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
		return AuthenticationToken.class.equals(authentication);
	}

}
