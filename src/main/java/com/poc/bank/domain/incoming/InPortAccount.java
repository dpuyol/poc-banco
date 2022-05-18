package com.poc.bank.domain.incoming;

import java.util.Optional;

import com.poc.bank.domain.dao.Account;
import com.poc.bank.domain.dto.AccountDTO;

public interface InPortAccount {

	AccountDTO create(AccountDTO account);

	AccountDTO addDepositIbanAccount(AccountDTO account);

	Optional<Account> findAccountByNumber(String number);

}
