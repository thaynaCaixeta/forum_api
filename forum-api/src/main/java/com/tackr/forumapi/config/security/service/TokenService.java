package com.tackr.forumapi.config.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tackr.forumapi.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expirationTime}")
	private String expirationTime;
	
	@Value("${forum.jwt.password}")
	private String password;

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date today = new Date();
		return Jwts.builder()
				.setIssuer("Forum api")
				.setSubject(user.getId().toString())
				.setIssuedAt(today)
				.setExpiration(new Date(today.getTime() + Long.parseLong(expirationTime)))
				.signWith(SignatureAlgorithm.HS256, password)
				.compact();
	}

}
