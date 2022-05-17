package com.poc.bank.adapter.infraestructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.bank.domain.dao.Account;

@Repository
public interface AccountDataRepository extends JpaRepository<Account, UUID> {

	@Query(value = "SELECT * FROM Account a WHERE a.iban = ?1", nativeQuery = true)
	Optional<Account> findAccountByNumber(String iban);

}
