package com.poc.bank.adapter.rest;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poc.bank.domain.dto.ErrorDTO;
import com.poc.bank.exception.AccounNotFoundException;
import com.poc.bank.exception.AccountRegisteredException;

@ControllerAdvice
public class AccountExceptionHandler {

	@ResponseBody
	@ExceptionHandler(AccountRegisteredException.class)
	public ResponseEntity<ErrorDTO> handleCreateAccountException(AccountRegisteredException ex) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.CONFLICT.value(), "Account with this iban number already in use",
				LocalTime.now().toString());
		return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
	}

	@ResponseBody
	@ExceptionHandler(AccounNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleCreateAccountException(AccounNotFoundException ex) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), "Account not found", LocalTime.now().toString());
		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}

}
