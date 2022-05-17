package com.poc.bank.adapter.rest;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poc.bank.domain.dto.ErrorDTO;
import com.poc.bank.exception.AccountRegisteredException;

@ControllerAdvice
public class AccountExceptionHandler {

	@ResponseBody
	@ExceptionHandler(AccountRegisteredException.class)
	public ResponseEntity<ErrorDTO> handleCreateAccountException(AccountRegisteredException ex) {
		ErrorDTO response = new ErrorDTO(HttpStatus.CONFLICT.value(), "Account with this iban number already in use",
				LocalTime.now().toString());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

}
