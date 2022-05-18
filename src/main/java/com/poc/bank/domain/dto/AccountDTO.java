package com.poc.bank.domain.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AccountDTO {

	private UUID id;
	private UUID userID;
	private String iban;
	private Double amount;
	private List<TransferDTO> transfer;

}
