package com.pccwglobal.assessment.marssitest.service;

import java.util.List;

import com.pccwglobal.assessment.marssitest.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.User;


public interface UserService {
	
	public List<User> getUsers(Integer page, Integer size);
	public User createUser(CreateUserRequest createUserRequest);
	public User getUserById(String id);
	public User updateUser(String id, UpdateUserRequest updateUserRequest);
	public User deleteUserById(String id);

	
}
