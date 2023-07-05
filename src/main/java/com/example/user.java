package com.example;

import java.util.Date;

public class user {
	int user_id;
	String first_name; 
	String last_name; 
	Date birthdate; 
	String mobile_number; 
	String mail;
	public user(int user_id, String first_name, String last_name, Date birthdate, String mobile_number, String mail) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthdate = birthdate;
		this.mobile_number = mobile_number;
		this.mail = mail;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	} 

}
