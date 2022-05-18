package com.poc.bank.adapter.infraestructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poc.bank.domain.dao.Transfer;

public interface TransferDataRepository extends JpaRepository<Transfer, UUID> {

	@Query(value = "SELECT * FROM transfer t WHERE t.iban_origin = ?1 OR t.iban_destination = ?1", nativeQuery = true)
	List<Transfer> findByIban(String iban);

}
