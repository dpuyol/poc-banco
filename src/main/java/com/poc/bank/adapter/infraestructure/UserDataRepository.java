package com.poc.bank.adapter.infraestructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.bank.domain.dao.User;

@Repository
public interface UserDataRepository extends JpaRepository<User, UUID> {

	@Query(value = "SELECT * FROM User u WHERE u.mail = ?1", nativeQuery = true)
	Optional<User> findUserByEmail(String email);
}
