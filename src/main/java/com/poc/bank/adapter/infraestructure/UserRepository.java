package com.poc.bank.adapter.infraestructure;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.bank.domain.model.User;
import com.poc.bank.domain.outgoing.OutPortUser;
import com.poc.bank.exception.UserNotFoundException;
import com.poc.bank.exception.UserRegisteredException;

@Component
public class UserRepository implements OutPortUser {

	@Autowired
	private DataUserRepository dataUserRepository;

	@Override
	public User save(User user) {

		if (dataUserRepository.findUserByEmail(user.getMail()) != null)
			throw new UserRegisteredException();

		return dataUserRepository.save(user);

	}

	@Override
	public User load(UUID userId) {
		return dataUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public void delete(UUID userId) {

		if (dataUserRepository.existsById(userId))
			dataUserRepository.deleteById(userId);
		else
			throw new UserNotFoundException();

	}

}
