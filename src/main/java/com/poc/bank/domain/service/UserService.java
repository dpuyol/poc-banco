package com.poc.bank.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.bank.domain.dao.User;
import com.poc.bank.domain.incoming.InPortUser;
import com.poc.bank.domain.outgoing.OutPortUser;
import com.poc.bank.exception.UserNotFoundException;
import com.poc.bank.exception.UserRegisteredException;

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

		if (outPortUser.findUserByEmail(user.getMail()).isPresent())
			throw new UserRegisteredException();

		return outPortUser.save(user);
	}

	@Override
	public void delete(UUID userId) {

		if (!outPortUser.existsById(userId))
			throw new UserNotFoundException();

		outPortUser.delete(userId);
	}

}
