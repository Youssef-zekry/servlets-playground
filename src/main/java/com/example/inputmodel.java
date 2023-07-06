package com.example;

import java.util.List;

public class inputmodel {
	@Override
	public String toString() {
		return "inputmodel [username=" + username + ", password=" + password + ", data=" + data + "]";
	}
	String username;
	String password;
	List<user> data;


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
		return data;
	}
	public void setUsers(List<user> users) {
		this.data = users;
	}
	
}
