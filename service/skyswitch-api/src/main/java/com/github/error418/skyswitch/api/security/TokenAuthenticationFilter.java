package com.github.error418.skyswitch.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String xToken = request.getHeader("X-AUTHTOKEN");
        
        Authentication auth = new AuthenticationToken(xToken);
        SecurityContextHolder.getContext().setAuthentication(auth);            
        
        filterChain.doFilter(request, response);
	}

}
