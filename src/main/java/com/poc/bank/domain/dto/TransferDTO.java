package com.poc.bank.domain.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class TransferDTO {

	private UUID id;
	private String ibanOrigin;
	private String ibanDestination;
	private Double amount;
	private boolean success;
}
