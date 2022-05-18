package com.poc.bank.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.bank.domain.dao.Account;
import com.poc.bank.domain.dao.Transfer;
import com.poc.bank.domain.dto.AccountDTO;
import com.poc.bank.domain.dto.TransferDTO;
import com.poc.bank.domain.incoming.InPortAccount;
import com.poc.bank.domain.incoming.InPortTransfer;
import com.poc.bank.domain.outgoing.OutPortTransfer;
import com.poc.bank.exception.AccounNotFoundException;
import com.poc.bank.exception.TransferNotMoneyException;
import com.poc.bank.util.OperationAccountEnum;

@Service
public class TransferService implements InPortTransfer {

	@Autowired
	private OutPortTransfer outPortTransfer;

	@Autowired
	private InPortAccount inPortAccount;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TransferDTO create(TransferDTO transferDTO) {

		/*
		 * TODO AccountNotFoundException with messages. It should describe if not find a
		 * iban origin or iban destination
		 */
		Account accountOrigin = inPortAccount.findAccountByNumber(transferDTO.getIbanOrigin())
				.orElseThrow(AccounNotFoundException::new);
		inPortAccount.findAccountByNumber(transferDTO.getIbanDestination()).orElseThrow(AccounNotFoundException::new);

		transferDTO.setSuccess(accountOrigin.getAmount() >= transferDTO.getAmount());
		if (!transferDTO.isSuccess()) {
			outPortTransfer.save(modelMapper.map(transferDTO, Transfer.class));
			throw new TransferNotMoneyException();
		}

		operationAccount(transferDTO, OperationAccountEnum.OPERATION_ACCOUNT_ORIGIN);
		operationAccount(transferDTO, OperationAccountEnum.OPERATION_ACCOUNT_DESTIONATION);

		outPortTransfer.save(modelMapper.map(transferDTO, Transfer.class));
		return transferDTO;
	}

	/*
	 * method used to deposit or withdraw money from an account
	 */
	private void operationAccount(TransferDTO transferDTO, OperationAccountEnum operation) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setIban(operation.equals(OperationAccountEnum.OPERATION_ACCOUNT_ORIGIN) ? transferDTO.getIbanOrigin()
				: transferDTO.getIbanDestination());
		accountDTO.setAmount(operation.equals(OperationAccountEnum.OPERATION_ACCOUNT_ORIGIN) ? -transferDTO.getAmount()
				: transferDTO.getAmount());
		inPortAccount.addDepositIbanAccount(accountDTO);
	}

}
