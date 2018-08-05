package com.pccwglobal.assessment.marssitest.service.impl;

import java.util.List;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pccwglobal.assessment.marssitest.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssitest.pojo.User;
import com.pccwglobal.assessment.marssitest.service.UserService;
import com.pccwglobal.assessment.marssitest.utils.JsonUtils;

@Service("restfulService")
public class UserRestfulServiceImpl implements UserService {

	@Value("${api-server-base-url}")
	private String API_SERVER_BASE_URL;

	@Override
	public List<User> getUsers(Integer page, Integer size) {
		try {
			String result = Request.Get(API_SERVER_BASE_URL + "?page=" + page + "&size=" + size)
					.setHeader("Content-Type", "application/json").execute().returnContent().asString();
			return JsonUtils.jsonToList(result, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User createUser(CreateUserRequest createUserRequest) {
		StringEntity stringEntity = new StringEntity(JsonUtils.objectToJson(createUserRequest), "utf-8");
		try {
			String result = Request.Put(API_SERVER_BASE_URL).setHeader("Content-type", "application/json")
					.body(stringEntity).execute().returnContent().asString();
			return JsonUtils.jsonToPojo(result, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserById(String id) {

		try {
			String result = Request.Get(API_SERVER_BASE_URL + "/" + id).setHeader("Content-type", "application/json")
					.execute().returnContent().asString();
			return JsonUtils.jsonToPojo(result, User.class);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public User updateUser(String id, UpdateUserRequest updateUserRequest) {

		StringEntity stringEntity = new StringEntity(JsonUtils.objectToJson(updateUserRequest), "utf-8");

		try {
			String result = Request.Post(API_SERVER_BASE_URL + "/" + id).setHeader("Content-type", "application/json")
					.body(stringEntity).execute().returnContent().asString();
			return JsonUtils.jsonToPojo(result, User.class);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public User deleteUserById(String id) {

		try {
			String result = Request.Delete(API_SERVER_BASE_URL + "/" + id).setHeader("Content-type", "application/json")
					.execute().returnContent().asString();
			return JsonUtils.jsonToPojo(result, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
