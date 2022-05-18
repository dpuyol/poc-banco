package com.poc.bank.domain.outgoing;

import com.poc.bank.domain.dao.Transfer;

public interface OutPortTransfer {

	Transfer save(Transfer transer);
}
