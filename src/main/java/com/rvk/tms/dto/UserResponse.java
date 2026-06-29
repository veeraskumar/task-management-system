package com.rvk.tms.dto;

import java.time.LocalDateTime;

import com.rvk.tms.enums.Role;

public record UserResponse(Long id, String name, String email, Role role, LocalDateTime createdAt) {

}
