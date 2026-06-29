package com.rvk.tms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(

		@NotBlank(message = "Name is Required") @Size(min = 3, message = "Name must contain minimum 3 characters") String name,

		@NotBlank(message = "Description is Required") String description) {

}
