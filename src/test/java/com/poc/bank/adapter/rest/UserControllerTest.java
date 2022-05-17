package com.poc.bank.adapter.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.bank.domain.dao.User;
import com.poc.bank.domain.dto.ErrorDTO;
import com.poc.bank.domain.dto.UserDTO;
import com.poc.bank.util.UserConstants;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private User user;
	private UUID userID;

	@BeforeAll
	void beforeAllTests() {
		user = UserConstants.createUser();
	}

	@Test
	@DisplayName("New user is created successfully")
	void testAlogginUserSucessfully() throws JsonProcessingException, Exception {

		MvcResult mvcResult = postCreateUser();

		// Check the http response and the json body
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		checkUserDTO(mvcResult);
	}

	@Test
	@DisplayName("Get a user previous created")
	void testBgetUser() throws Exception {

		MvcResult mvcResult = getUser();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		checkUserDTO(mvcResult);
	}

	@Test
	@DisplayName("User tries to create with user email already created in")
	void testCUserSameMail() throws JsonProcessingException, Exception {

		MvcResult mvcResult = postCreateUser();

		assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
		assertNotNull(mvcResult.getResponse().getContentAsString());

		ErrorDTO errorDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorDTO.class);
		assertEquals(HttpStatus.CONFLICT.value(), errorDTO.getStatus());
	}

	@Test
	@DisplayName("Delete a user already created")
	void testDDeleteUser() throws JsonProcessingException, Exception {

		MvcResult mvcResult = deleteUser();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	@DisplayName("Try delete user doesn't created")
	void testEDeleteUserNoExists() throws JsonProcessingException, Exception {

		MvcResult mvcResult = deleteUser();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	@DisplayName("Get a user doesn't created")
	void testFGetUserNotExists() throws JsonProcessingException, Exception {

		MvcResult mvcResult = getUser();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
	}

	private MvcResult getUser() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(get(UserConstants.API_VERSION + UserConstants.API_USERS + "/" + userID.toString()))
				.andReturn();
		return mvcResult;
	}

	private MvcResult deleteUser() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(delete(UserConstants.API_VERSION + UserConstants.API_USERS + "/" + userID.toString()))
				.andReturn();
		return mvcResult;
	}

	private MvcResult postCreateUser() throws Exception, JsonProcessingException {
		MvcResult mvcResult = this.mockMvc
				.perform(post(UserConstants.API_VERSION + UserConstants.API_USERS)
						.content(objectMapper.writeValueAsBytes(user)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		return mvcResult;
	}

	// Password is not checked because it never returns from the server in the
	// dto
	private void checkUserDTO(MvcResult mvcResult)
			throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		assertNotNull(mvcResult.getResponse().getContentAsString());
		UserDTO userDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);
		assertEquals(user.getName(), userDTO.getName());
		assertEquals(user.getSurname(), userDTO.getSurname());
		assertEquals(user.getMail(), userDTO.getMail());
		assertEquals(user.getAddress(), userDTO.getAddress());

		// The next test already get id
		userID = userDTO.getId();
	}
}
