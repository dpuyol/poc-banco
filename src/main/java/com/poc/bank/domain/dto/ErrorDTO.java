package com.poc.bank.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ErrorDTO {

	private Integer status;
	private String message;
	private String timestamp;

}
