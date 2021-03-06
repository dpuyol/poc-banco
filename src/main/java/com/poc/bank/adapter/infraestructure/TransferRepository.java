package com.poc.bank.adapter.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.bank.domain.dao.Transfer;
import com.poc.bank.domain.outgoing.OutPortTransfer;

@Component
public class TransferRepository implements OutPortTransfer {

	@Autowired
	private TransferDataRepository transferDataRepository;

	@Override
	public Transfer save(Transfer transfer) {
		return transferDataRepository.save(transfer);
	}

	@Override
	public List<Transfer> getTransfer(String iban) {
		return transferDataRepository.findByIban(iban);
	}
}
