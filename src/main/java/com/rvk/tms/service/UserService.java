package com.rvk.tms.service;

import java.util.List;

import com.rvk.tms.dto.UserRegisterRequest;
import com.rvk.tms.dto.UserResponse;
import com.rvk.tms.dto.UserUpdateRequest;

public interface UserService {

	UserResponse resgisterUser(UserRegisterRequest request);

	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long id);

	UserResponse updateUser(Long id, UserUpdateRequest request);

	void deleteUser(Long id);
}