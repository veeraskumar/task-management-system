package com.rvk.tms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(

		@NotBlank(message = "Name is required") @Size(min = 3, message = "Name must contain minimum 3 characters") String name,

		@NotBlank(message = "Email is required") @Email(message = "Invalid Email") String email,

		@NotBlank(message = "Password is required") @Size(min = 6, max = 18, message = "Password must be between 6 and 72 characters long") String password) {
}
