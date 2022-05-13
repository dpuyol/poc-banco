package com.poc.bank.domain.outgoing;

import java.util.UUID;

import com.poc.bank.domain.model.User;

public interface OutPortUser {

	User load(UUID uid);

	User save(User user);

	void delete(UUID userId);
}
