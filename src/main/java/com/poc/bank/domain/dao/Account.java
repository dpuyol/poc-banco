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
@Table(name = "account")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private @Getter UUID id;

	@Column(name = "userId")
	private @Getter @Setter UUID userID;

	@Column(name = "number", unique = true)
	private @Getter @Setter String number;

	@Column(name = "amount")
	private @Getter @Setter Double amount;

	public void initializeAmount() {
		this.setAmount(0.00);
	}
}
