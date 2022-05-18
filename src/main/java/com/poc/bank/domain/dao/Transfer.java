package com.poc.bank.domain.dao;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "transfer")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private @Getter UUID id;

	@Column(name = "ibanOrigin")
	private @Getter @Setter String ibanOrigin;

	@Column(name = "ibanDestination")
	private @Getter @Setter String ibanDestination;

	@Column(name = "amount")
	private @Getter @Setter Double amount;

	@Column(name = "success")
	private @Getter @Setter boolean success;

}
