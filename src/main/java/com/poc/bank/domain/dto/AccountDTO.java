package com.poc.bank.domain.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AccountDTO {

	private UUID id;
	private UUID userID;
	private String number;

}
