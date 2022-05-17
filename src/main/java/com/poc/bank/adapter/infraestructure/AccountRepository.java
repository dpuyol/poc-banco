package com.poc.bank.adapter.infraestructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.bank.domain.dao.Account;
import com.poc.bank.domain.outgoing.OutPortAccount;

@Component
public class AccountRepository implements OutPortAccount {

	@Autowired
	private AccountDataRepository accountDataRepository;

	@Override
	public Optional<Account> findAccountByNumber(String number) {
		return accountDataRepository.findAccountByNumber(number);
	}

	@Override
	public Account save(Account account) {
		return accountDataRepository.save(account);
	}

	@Override
	public Optional<Account> findById(UUID accountID) {
		return accountDataRepository.findById(accountID);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountDataRepository.saveAndFlush(account);
	}

}
