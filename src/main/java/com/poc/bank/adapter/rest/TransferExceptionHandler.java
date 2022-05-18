package com.poc.bank.adapter.rest;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poc.bank.domain.dto.ErrorDTO;
import com.poc.bank.exception.TransferNotMoneyException;

@ControllerAdvice
public class TransferExceptionHandler {

	@ResponseBody
	@ExceptionHandler(TransferNotMoneyException.class)
	public ResponseEntity<ErrorDTO> handleTransferNotMoneyException(TransferNotMoneyException ex) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.CONFLICT.value(),
				"Not enough money in the account origin for the transfer", LocalTime.now().toString());
		return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
	}

}
