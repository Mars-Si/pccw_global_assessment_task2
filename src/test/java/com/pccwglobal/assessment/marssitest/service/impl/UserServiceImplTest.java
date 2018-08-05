package com.pccwglobal.assessment.marssitest.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pccwglobal.assessment.marssitest.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.User;
import com.pccwglobal.assessment.marssitest.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	@Qualifier("localService")
	private UserService localUserService;
	@Autowired
	@Qualifier("restfulService")
	private UserService restfulUserService;

	
	@Test	
	// Test listing all users
	public void testListUsers() throws Exception {
		int page = 1;
		int size = 1;
		List<User> restfulUsers = restfulUserService.getUsers(page, size);
		List<User> localUsers = localUserService.getUsers(page, size);
		assertThat(restfulUsers.size(), equalTo(localUsers.size()));
		for (int i = 0; i < restfulUsers.size(); i++) {
			assertThat(restfulUsers.get(i), equalTo(localUsers.get(i)));
		}

	}

	// Test creating a new user
	@Test
	public void testCreateUser() throws Exception {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setEmail("newuser@newuser.com");
		userRequest.setName("newuser");
		userRequest.setPassword("67890");
		userRequest.setUsername("newuser");
		User user = restfulUserService.createUser(userRequest);
		assertNotNull(user);
		assertThat(user, equalTo(localUserService.getUserById(user.getId())));
	}

	// Test finding a user via id
	@Test
	public void testGetUserById() throws Exception {
		String id = "ee1b8e7121714fc9a5b7f1424e7d0062";
		User user = restfulUserService.getUserById(id);
		assertThat(user, equalTo(localUserService.getUserById(id)));
	}

	// Test updating a user
	@Test
	public void testUpdateUser() throws Exception {
		String id = "406ceab93557456183f01fd2862b3c07";
		UpdateUserRequest userRequest = new UpdateUserRequest();
		userRequest.setEmail("updateduser4@updateduser2.com");
		userRequest.setName("updateduser4");
		userRequest.setPassword("78901");
		userRequest.setUsername("updateduser4");
		User oldUser = localUserService.getUserById(id);
		User updatedUser = restfulUserService.updateUser(id, userRequest);
		assertNotEquals(oldUser, updatedUser);
		assertThat(updatedUser, equalTo(localUserService.getUserById(id)));
	}

	// Test deleting a user
	@Test
	public void testDeleteUserById() throws Exception {
		String id = "9a6e1003a99e443a94ce3a65ec2b8057";
		User user = restfulUserService.getUserById(id);
		assertNotNull(user);
		restfulUserService.deleteUserById(id);
		assertNull(localUserService.getUserById(id));

	}
	
}
