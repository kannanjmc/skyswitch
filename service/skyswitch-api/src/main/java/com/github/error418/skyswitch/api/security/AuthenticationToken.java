package com.github.error418.skyswitch.api.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationToken implements Authentication {

	private static final long serialVersionUID = 6439226621691453335L;

	private boolean authenticated;
	private final String credentials;
	private List<GrantedAuthority> authorities;
	
	public AuthenticationToken(String credentials, String... authorityNames) {
		this.authenticated = false;
		this.credentials = credentials;
		this.authorities = new ArrayList<GrantedAuthority>();
		
		for(String authority : authorityNames) {
			this.authorities.add(new SimpleGrantedAuthority(authority));
		}
	}
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}

}
