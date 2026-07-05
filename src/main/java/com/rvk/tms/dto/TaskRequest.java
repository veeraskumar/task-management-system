package com.rvk.tms.dto;

import java.time.LocalDate;

import com.rvk.tms.enums.Status;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequest(

		@NotBlank(message = "Name is Required") @Size(min = 3, message = "Name must contain minimum 3 characters") String title,

		@NotBlank(message = "Description is Required") String description,

		@NotNull(message = "Status is Required") Status status,

		@NotNull(message = "Due date is required") @FutureOrPresent(message = "Due date must be in the present or future") LocalDate dueDate,

		@NotNull(message = "Category Id is Required") Long categoryId) {
}
