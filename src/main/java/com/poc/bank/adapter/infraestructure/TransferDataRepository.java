package com.poc.bank.adapter.infraestructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.bank.domain.dao.Transfer;

public interface TransferDataRepository extends JpaRepository<Transfer, UUID> {

}
