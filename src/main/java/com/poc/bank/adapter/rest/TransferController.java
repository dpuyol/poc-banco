package com.poc.bank.adapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.bank.domain.dto.TransferDTO;
import com.poc.bank.domain.incoming.InPortTransfer;

@RestController
@RequestMapping("/v1/transfers")
public class TransferController {

	Logger logger = LoggerFactory.getLogger(TransferController.class);

	@Autowired
	private InPortTransfer transferUseCase;

	@PostMapping
	public ResponseEntity<TransferDTO> createTransfer(@RequestBody TransferDTO transferDTO) {
		logger.info("Create a new transfer: {}", transferDTO);
		return new ResponseEntity<>(transferUseCase.create(transferDTO), HttpStatus.CREATED);
	}
}
