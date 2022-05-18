package com.poc.bank.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.bank.domain.dao.Account;
import com.poc.bank.domain.dto.AccountDTO;
import com.poc.bank.domain.incoming.InPortAccount;
import com.poc.bank.domain.outgoing.OutPortAccount;
import com.poc.bank.exception.AccounNotFoundException;
import com.poc.bank.exception.AccountRegisteredException;

@Service
public class AccountService implements InPortAccount {

	@Autowired
	private OutPortAccount outPortAccount;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AccountDTO create(AccountDTO accountDTO) {

		if (outPortAccount.findAccountByNumber(accountDTO.getIban()).isPresent())
			throw new AccountRegisteredException();

		Account account = modelMapper.map(accountDTO, Account.class);
		account.initializeAmount();
		return modelMapper.map(outPortAccount.save(account), AccountDTO.class);
	}

	@Override
	public AccountDTO addDepositAccount(AccountDTO accountDTO) {
		Account account = outPortAccount.findById(accountDTO.getId()).orElseThrow(AccounNotFoundException::new);
		account.setAmount(Double.sum(account.getAmount(), accountDTO.getAmount()));
		return modelMapper.map(outPortAccount.updateAccount(account), AccountDTO.class);
	}

}
