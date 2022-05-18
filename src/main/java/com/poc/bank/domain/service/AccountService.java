package com.poc.bank.domain.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.bank.domain.dao.Account;
import com.poc.bank.domain.dto.AccountDTO;
import com.poc.bank.domain.incoming.InPortAccount;
import com.poc.bank.domain.incoming.InPortTransfer;
import com.poc.bank.domain.outgoing.OutPortAccount;
import com.poc.bank.exception.AccounNotFoundException;
import com.poc.bank.exception.AccountRegisteredException;

@Service
public class AccountService implements InPortAccount {

	Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private OutPortAccount outPortAccount;

	@Autowired
	private InPortTransfer inPortTransfer;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AccountDTO create(AccountDTO accountDTO) {

		/*
		 * TODO: Check if UserID in accountDTO already registered
		 */

		if (outPortAccount.findAccountByNumber(accountDTO.getIban()).isPresent())
			throw new AccountRegisteredException();

		Account account = modelMapper.map(accountDTO, Account.class);
		account.initializeAmount();
		return modelMapper.map(outPortAccount.save(account), AccountDTO.class);
	}

	@Override
	public AccountDTO addDepositIbanAccount(AccountDTO accountDTO) {

		logger.info("Deposit in iban account: {}", accountDTO);
		Account account = outPortAccount.findAccountByNumber(accountDTO.getIban())
				.orElseThrow(AccounNotFoundException::new);
		account.setAmount(Double.sum(account.getAmount(), accountDTO.getAmount()));

		return modelMapper.map(outPortAccount.updateAccount(account), AccountDTO.class);
	}

	@Override
	public Optional<Account> findAccountByNumber(String number) {
		return outPortAccount.findAccountByNumber(number);
	}

	@Override
	public AccountDTO getAccountAndMoves(String iban) {
		AccountDTO accountDTO = modelMapper.map(findAccountByNumber(iban).orElseThrow(AccounNotFoundException::new),
				AccountDTO.class);
		accountDTO.setTransfer(inPortTransfer.getTransfer(iban));
		return accountDTO;
	}

}
