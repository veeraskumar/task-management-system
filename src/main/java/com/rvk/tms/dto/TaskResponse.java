package com.rvk.tms.dto;

import java.time.LocalDate;

import com.rvk.tms.enums.Status;

public record TaskResponse(Long id, String title, String description, Status status, LocalDate dueDate,
		UserResponse user, CategoryResponse category) {

}
