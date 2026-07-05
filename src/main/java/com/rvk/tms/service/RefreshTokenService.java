package com.rvk.tms.service;

import com.rvk.tms.entity.RefreshToken;
import com.rvk.tms.entity.User;

public interface RefreshTokenService {

	RefreshToken createRefreshToken(User user);

	RefreshToken verifyToken(String token);

	void revokeToken(String token);

}