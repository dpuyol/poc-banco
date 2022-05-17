package com.poc.bank.adapter.infraestructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.bank.domain.dao.User;
import com.poc.bank.domain.outgoing.OutPortUser;
import com.poc.bank.exception.UserNotFoundException;

@Component
public class UserRepository implements OutPortUser {

	@Autowired
	private UserDataRepository dataUserRepository;

	@Override
	public User save(User user) {
		return dataUserRepository.save(user);
	}

	@Override
	public User load(UUID userId) {
		return dataUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public void delete(UUID userId) {
		dataUserRepository.deleteById(userId);
	}

	@Override
	public boolean existsById(UUID userId) {
		return dataUserRepository.existsById(userId);
	}

	@Override
	public Optional<User> findUserByEmail(String mail) {
		return dataUserRepository.findUserByEmail(mail);
	}

}
