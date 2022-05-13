package com.poc.bank.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.bank.domain.incoming.InPortUser;
import com.poc.bank.domain.model.User;
import com.poc.bank.domain.outgoing.OutPortUser;

@Service
public class UserService implements InPortUser {

	@Autowired
	private OutPortUser outPortUser;

	@Override
	public User load(UUID uid) {
		return outPortUser.load(uid);
	}

	@Override
	public User create(User user) {
		return outPortUser.save(user);
	}

	@Override
	public void delete(UUID userId) {
		outPortUser.delete(userId);

	}

}
