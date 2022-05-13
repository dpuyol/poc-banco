package com.poc.bank.adapter.rest;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poc.bank.domain.dto.ErrorDTO;
import com.poc.bank.exception.UserNotFoundException;
import com.poc.bank.exception.UserRegisteredException;

@ControllerAdvice
public class UserExceptionHandler {

	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException ex) {
		ErrorDTO response = new ErrorDTO(HttpStatus.NOT_FOUND.value(), "User not found", LocalTime.now().toString());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@ExceptionHandler(UserRegisteredException.class)
	public ResponseEntity<ErrorDTO> handleUserRegisteredException(UserRegisteredException ex) {
		ErrorDTO response = new ErrorDTO(HttpStatus.CONFLICT.value(), "User with this email already in use",
				LocalTime.now().toString());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

}
