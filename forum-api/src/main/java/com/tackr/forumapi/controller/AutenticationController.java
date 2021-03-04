package com.tackr.forumapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tackr.forumapi.config.security.service.TokenService;
import com.tackr.forumapi.controller.dto.TokenDto;
import com.tackr.forumapi.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm) {

		UsernamePasswordAuthenticationToken authenticationData = loginForm.convert();

		try {

			Authentication authentication = authManager.authenticate(authenticationData);

			String token = tokenService.generateToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
