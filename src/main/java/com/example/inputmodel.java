package com.example;

import java.util.List;

public class inputmodel {
	String username;
	String password;
	List<user> users;

	
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
	public List<user> getUsers() {
		return users;
	}
	public void setUsers(List<user> users) {
		this.users = users;
	}
}
