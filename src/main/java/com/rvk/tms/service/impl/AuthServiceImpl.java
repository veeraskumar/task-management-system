package com.rvk.tms.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.rvk.tms.dto.LoginRequest;
import com.rvk.tms.dto.LoginResponse;
import com.rvk.tms.dto.LogoutRequest;
import com.rvk.tms.entity.RefreshToken;
import com.rvk.tms.entity.User;
import com.rvk.tms.security.CustomUserDetails;
import com.rvk.tms.security.CustomUserDetailsService;
import com.rvk.tms.security.JwtService;
import com.rvk.tms.service.AuthService;
import com.rvk.tms.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final CustomUserDetailsService customUserDetailsService;
	private final JwtService jwtService;
	private final RefreshTokenService refreshTokenService;

	@Override
	public LoginResponse login(LoginRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

		CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService
				.loadUserByUsername(request.email());

		User user = customUserDetails.getUser();

		String accessToken = jwtService.generateToken(customUserDetails);

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

		return new LoginResponse(accessToken, refreshToken.getToken());
	}

	@Override
	public void revokeToken(LogoutRequest request) {
		refreshTokenService.revokeToken(request.refreshToken());
	}

}
