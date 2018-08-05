package com.pccwglobal.assessment.marssitest.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Column(nullable = false)
	private String email;

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode() + this.email.hashCode() + this.name.hashCode() + this.password.hashCode()
				+ this.username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		if (this.id.equals(user.getId()) && this.email.equals(user.getEmail()) && this.name.equals(user.getName())
				&& this.password.equals(user.getPassword()) && this.username.equals(user.getUsername()))
			return true;

		return false;
	}


	

}
