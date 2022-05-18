package com.poc.bank.domain.incoming;

import com.poc.bank.domain.dto.TransferDTO;

public interface InPortTransfer {

	TransferDTO create(TransferDTO transferDTO);

}
