package com.rvk.tms.mapper;

import com.rvk.tms.dto.UserResponse;
import com.rvk.tms.entity.User;

public class UserMapper {

	public static UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getCreatedAt());
	}
}
