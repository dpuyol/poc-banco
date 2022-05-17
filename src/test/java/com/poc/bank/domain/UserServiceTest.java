package com.poc.bank.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.poc.bank.domain.dao.User;
import com.poc.bank.domain.outgoing.OutPortUser;
import com.poc.bank.domain.service.UserService;
import com.poc.bank.exception.UserNotFoundException;
import com.poc.bank.exception.UserRegisteredException;
import com.poc.bank.util.UserConstants;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private OutPortUser outPortUserMock;

	@InjectMocks
	private UserService userService;

	@Mock
	private User expectedUserMock;
	@Mock
	private User existentUser;

	private User user;
	private UUID userID;

	@BeforeEach
	private void createUser() {
		userID = UUID.randomUUID();
		user = UserConstants.createUser();
	}

	@Test
	void itShouldReturnNonNullLoad() {
		// Prepare state initial
		when(outPortUserMock.load(userID)).thenReturn(user);
		// Test trigger
		User output = userService.load(userID);

		assertNotNull(output);
	}

	@Test
	void itShouldLoadUserId() {
		when(outPortUserMock.load(userID)).thenReturn(user);

		// Trigger White box
		User output = userService.load(userID);

		assertThat(output).isEqualTo(user);
	}

	@Test
	void itShouldThrowExceptionCreate() {
		when(outPortUserMock.findUserByEmail(UserConstants.MAIL)).thenReturn(Optional.of(existentUser));

		assertThrows(UserRegisteredException.class, () -> userService.create(user));
	}

	@Test
	void itShouldReturnNonNullWhenCreate() {
		when(outPortUserMock.save(user)).thenReturn(expectedUserMock);

		User output = userService.create(user);

		assertNotNull(output);
	}

	@Test
	void itShouldCreateUser() {
		when(outPortUserMock.save(user)).thenReturn(expectedUserMock);

		User output = userService.create(user);

		assertThat(output).isEqualTo(expectedUserMock);
	}

	@Test
	void itShouldThrowExceptionDelete() {
		when(outPortUserMock.existsById(userID)).thenReturn(false);

		assertThrows(UserNotFoundException.class, () -> userService.delete(userID));
	}

	@Test
	void itShouldDeleteUserWhenUserExists() {
		when(outPortUserMock.existsById(userID)).thenReturn(true);

		userService.delete(userID);

		verify(outPortUserMock).delete(userID);

	}
}
