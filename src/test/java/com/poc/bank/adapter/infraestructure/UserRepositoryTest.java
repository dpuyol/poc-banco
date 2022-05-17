package com.poc.bank.adapter.infraestructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.bank.domain.dao.User;
import com.poc.bank.util.UserConstants;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	private User user;

	@BeforeAll
	void createUsers() {
		user = new User();
		user.setName(UserConstants.NAME);
		user.setSurname(UserConstants.SURNAME);
		user.setMail(UserConstants.MAIL);
		user.setAddress(UserConstants.ADDRESS);
		user.setPassword(UserConstants.PASSWORD);

		userRepository.save(user);
	}

	@Test
	@DisplayName("Search a user by email (native query)")
	void test() {
		User result = userRepository.findUserByEmail(UserConstants.MAIL);
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getSurname(), result.getSurname());
		assertEquals(user.getMail(), result.getMail());
		assertEquals(user.getAddress(), result.getAddress());
	}

}
