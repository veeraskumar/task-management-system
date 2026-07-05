package com.rvk.tms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
		@NotBlank(message = "Email is required") @Email(message = "Please Eenter valid Email") String email,
		@NotBlank(message = "Password is Requried") @Size(min = 6, message = "Min 6 Characters") String password) {

}
