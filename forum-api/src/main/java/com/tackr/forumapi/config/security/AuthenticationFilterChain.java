package com.tackr.forumapi.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.tackr.forumapi.config.security.service.TokenService;

public class AuthenticationFilterChain extends OncePerRequestFilter {

	private TokenService tokenService;

	public AuthenticationFilterChain(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromHeader(request);

		boolean isValid = tokenService.isTokenValid(token);

		filterChain.doFilter(request, response);
	}
	
	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}
}
