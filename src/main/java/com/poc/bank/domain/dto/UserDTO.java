package com.poc.bank.domain.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

	private UUID id;
	private String name;
	private String surname;
	private String mail;
	private String address;

}
