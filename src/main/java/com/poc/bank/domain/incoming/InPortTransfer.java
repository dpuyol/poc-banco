package com.poc.bank.domain.incoming;

import java.util.List;

import com.poc.bank.domain.dto.TransferDTO;

public interface InPortTransfer {

	TransferDTO create(TransferDTO transferDTO);

	List<TransferDTO> getTransfer(String iban);

}
