package com.poc.bank.domain.outgoing;

import java.util.List;

import com.poc.bank.domain.dao.Transfer;

public interface OutPortTransfer {

	Transfer save(Transfer transer);

	List<Transfer> getTransfer(String iban);
}
