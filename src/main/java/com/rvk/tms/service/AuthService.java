package com.rvk.tms.service;

import com.rvk.tms.dto.LoginRequest;
import com.rvk.tms.dto.LoginResponse;
import com.rvk.tms.dto.LogoutRequest;

public interface AuthService {
	
	LoginResponse login(LoginRequest request);
	
	void revokeToken(LogoutRequest request);
}
