package com.poc.bank.domain.model;

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
@Table(name = "user")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private @Getter UUID id;

	@Column(name = "name")
	private @Getter @Setter String name;

	@Column(name = "surname")
	private @Getter @Setter String surname;

	@Column(name = "mail", unique = true)
	private @Getter @Setter String mail;

	@Column(name = "address")
	private @Getter @Setter String address;

	@Column(name = "password")
	private @Getter @Setter String password;

}
