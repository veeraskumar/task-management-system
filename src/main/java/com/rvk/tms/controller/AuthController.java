package com.rvk.tms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rvk.tms.dto.LoginRequest;
import com.rvk.tms.dto.LoginResponse;
import com.rvk.tms.dto.LogoutRequest;
import com.rvk.tms.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
	    authService.revokeToken(request);
	    return ResponseEntity.ok().build();
	}
}
