package com.poc.bank.domain.incoming;

import java.util.UUID;

import com.poc.bank.domain.dao.User;

public interface InPortUser {

	User load(UUID uid);

	User create(User user);

	void delete(UUID userId);
}
