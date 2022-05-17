package com.poc.bank.util;

import com.poc.bank.domain.dao.User;

public class UserConstants {

	public static final String API_VERSION = "/v1";
	public static final String API_USERS = "/users";

	public static final String NAME = "Pepe";
	public static final String SURNAME = "Ramirez";
	public static final String MAIL = "pepe@pepe";
	public static final String ADDRESS = "street and number";
	public static final String PASSWORD = "12345";

	public static User createUser() {
		User user = new User();
		user.setName(UserConstants.NAME);
		user.setSurname(UserConstants.SURNAME);
		user.setMail(UserConstants.MAIL);
		user.setAddress(UserConstants.ADDRESS);
		user.setPassword(UserConstants.PASSWORD);

		return user;
	}
}
