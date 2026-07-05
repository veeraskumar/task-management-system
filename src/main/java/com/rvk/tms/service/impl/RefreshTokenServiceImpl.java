package com.rvk.tms.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvk.tms.entity.RefreshToken;
import com.rvk.tms.entity.User;
import com.rvk.tms.exception.ResourceNotFoundException;
import com.rvk.tms.repository.RefreshTokenRepository;
import com.rvk.tms.security.JwtService;
import com.rvk.tms.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Value("${jwt.refresh-expiration}")
	private Long refreshExpiration;
	
	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtService jwtService;

	@Override
	@Transactional
	public RefreshToken createRefreshToken(User user) {

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(jwtService.generateRefreshToken(user));
		refreshToken.setCreatedAt(LocalDateTime.now());
		refreshToken.setExpiryDate(LocalDateTime.now().plus(Duration.ofMillis(refreshExpiration)));
		refreshToken.setRevoked(false);
		refreshToken.setUser(user);

		RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

		return savedRefreshToken;

	}

	@Override
	public RefreshToken verifyToken(String token) {
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new ResourceNotFoundException("Token is not exist"));

		if (refreshToken.isRevoked()) {
			throw new AccessDeniedException("User is not authenticated");
		}

		if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new AccessDeniedException("User is not authenticated");
		}

		return refreshToken;
	}

	@Override
	@Transactional
	public void revokeToken(String token) {
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new ResourceNotFoundException("Token is not exist"));
		refreshToken.setRevoked(true);
		refreshTokenRepository.save(refreshToken);
	}

}