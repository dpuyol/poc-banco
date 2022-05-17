package com.poc.bank.adapter.rest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.bank.domain.dto.AccountDTO;
import com.poc.bank.domain.incoming.InPortAccount;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private InPortAccount accountUseCase;

	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO account) {
		logger.info("Create a new account: {}", account);
		return new ResponseEntity<>(modelMapper.map(accountUseCase.create(account), AccountDTO.class),
				HttpStatus.CREATED);
	}
}