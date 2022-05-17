package com.poc.bank.adapter.rest;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.bank.domain.dao.User;
import com.poc.bank.domain.dto.UserDTO;
import com.poc.bank.domain.incoming.InPortUser;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private InPortUser userUseCase;

	@GetMapping(value = "/{userID}")
	public ResponseEntity<UserDTO> getUserId(@PathVariable("userID") UUID userId) {
		logger.info("Get a userID: {}", userId);
		return new ResponseEntity<>(modelMapper.map(userUseCase.load(userId), UserDTO.class), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
		logger.info("Create a new user: {}", user);
		return new ResponseEntity<>(modelMapper.map(userUseCase.create(user), UserDTO.class), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{userID}")
	public HttpStatus deleteUserId(@PathVariable("userID") UUID userId) {
		logger.info("Delete a userID: {}", userId);
		userUseCase.delete(userId);
		return HttpStatus.OK;
	}

}
