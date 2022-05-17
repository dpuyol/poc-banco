package com.poc.bank.domain.outgoing;

import java.util.Optional;
import java.util.UUID;

import com.poc.bank.domain.dao.Account;

public interface OutPortAccount {

	Optional<Account> findAccountByNumber(String number);

	Optional<Account> findById(UUID accountID);

	Account save(Account account);

	Account updateAccount(Account account);
}
