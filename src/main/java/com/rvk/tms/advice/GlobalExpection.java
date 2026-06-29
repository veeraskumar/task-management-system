package com.rvk.tms.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rvk.tms.dto.ErrorResponse;
import com.rvk.tms.exception.ResourceAlreadyExistsException;
import com.rvk.tms.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExpection {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(
			ResourceAlreadyExistsException exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpStatus.CONFLICT.value(),
				LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
	}
}