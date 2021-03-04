package com.tackr.forumapi.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tackr.forumapi.config.security.service.TokenService;
import com.tackr.forumapi.entity.User;
import com.tackr.forumapi.repository.UserRepository;

public class AuthenticationFilterChain extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UserRepository userRepository;

	public AuthenticationFilterChain(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromHeader(request);

		boolean isValid = tokenService.isTokenValid(token);
		
		if (isValid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
	private void authenticateClient(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userRepository.findById(userId).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
				null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
