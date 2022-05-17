package com.poc.bank.domain.incoming;

import com.poc.bank.domain.dto.AccountDTO;

public interface InPortAccount {

	AccountDTO create(AccountDTO account);

	AccountDTO addDepositAccount(AccountDTO account);

}
