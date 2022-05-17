package com.poc.bank.domain.outgoing;

import java.util.Optional;

import com.poc.bank.domain.dao.Account;

public interface OutPortAccount {

	Optional<Account> findAccountByNumber(String number);

	Account save(Account account);
}
