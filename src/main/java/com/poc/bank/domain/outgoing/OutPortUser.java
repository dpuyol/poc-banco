package com.poc.bank.domain.outgoing;

import java.util.Optional;
import java.util.UUID;

import com.poc.bank.domain.dao.User;

public interface OutPortUser {

	User load(UUID uid);

	User save(User user);

	void delete(UUID userId);

	boolean existsById(UUID userId);

	Optional<User> findUserByEmail(String mail);

}
