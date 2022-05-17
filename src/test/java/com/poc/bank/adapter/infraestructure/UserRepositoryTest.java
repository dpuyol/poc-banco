package com.poc.bank.adapter.infraestructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
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
	private UserRepository userRepository;;
	private User user;

	@BeforeAll
	void createUsers() {
		user = UserConstants.createUser();
		userRepository.save(user);
	}

	@Test
	void itShouldReturnUser() {
		Optional<User> result = userRepository.findUserByEmail(UserConstants.MAIL);
		assertThat(result).isNotEmpty();
	}

	@Test
	void itShouldReturnTheExpectedUser() {
		User result = userRepository.findUserByEmail(UserConstants.MAIL).get();

		assertEquals(UserConstants.NAME, result.getName());
		assertEquals(UserConstants.SURNAME, result.getSurname());
		assertEquals(UserConstants.MAIL, result.getMail());
		assertEquals(UserConstants.ADDRESS, result.getAddress());
		assertEquals(UserConstants.PASSWORD, result.getPassword());
	}

}
